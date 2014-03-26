package ee.soolep.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ant {

    private int x;
    private int y;
    public final int cellSize;
    private Cell cell;
    private int energy;
    private int age;

    private Nest nest;

    public static final int maxLength = 2;
    public final Rectangle rect;
    Random random;

    boolean searching;
    boolean food;

    /** Class constructor
     * @param x location on x axis
     * @param y location on y axis
     */
    public Ant(int x, int y, int cellSize, Nest nest) {
        this.x = x;
        this.y = y;
        this.nest = nest;
        this.cellSize = cellSize;
        this.rect = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
        this.rect.setFill(Color.ALICEBLUE);
        this.searching = true;
        this.food = false;
        this.energy = 200;
        this.random = new Random();
        this.age = 0;
    }

    public void update(Cell[][] cells){
        this.energy--;
        cell = cells[this.x][this.y];
        cell.visit(this);
        if (food && this.x == nest.x && this.y == nest.y){
            this.searching = true;
            this.food = false;
            nest.addFood();
        }
        move(observe(cells));
    }

    private void move(int direction) {
        random = new Random();
        int length = random.nextInt(maxLength);
        switch (direction){
            case Direction.NORTH:
                this.y -= length;
                break;
            case Direction.SOUTH:
                this.y += length;
                break;
            case Direction.EAST:
                this.x += length;
                break;
            case Direction.WEST:
                this.x -= length;
                break;
            default:
                System.out.println("mingi error liikumisega: " + toString());
                break;
        }
        checkBorders(this.x, this.y);
        this.rect.setX(this.x * cellSize);
        this.rect.setY(this.y * cellSize);
        this.energy -= length;
    }

    private void checkBorders(int x, int y) {
        if (x < maxLength) this.x = maxLength;
        if (y < maxLength) this.y = maxLength;
        if (x > AntSimulation.width - maxLength) this.x = AntSimulation.width - maxLength;
        if (y > AntSimulation.height - maxLength) this.y = AntSimulation.height - maxLength;
    }

    private int observe(Cell[][] cells) {
        int direction = Direction.NO_DIR;
        if (searching){
            if (getFoodPheromoneDirection(cells) == Direction.NO_DIR){
                if (random.nextBoolean()) direction = random.nextInt(4);
                else direction = oppositeDirection(getSearchPheromoneDirection(cells));
            } else {
                direction = getFoodPheromoneDirection(cells);
            }
        } else if(food){
            if (getSearchPheromoneDirection(cells) == Direction.NO_DIR) {
                direction = random.nextInt(4);
            } else {
                direction = getSearchPheromoneDirection(cells);
            }
        }
        if (direction == Direction.NO_DIR) direction = random.nextInt(4);
        return direction;
    }

    private int oppositeDirection(int direction){
        switch (direction){
            case Direction.EAST:
                return Direction.WEST;
            case Direction.WEST:
                return Direction.EAST;
            case Direction.NORTH:
                return Direction.SOUTH;
            case Direction.SOUTH:
                return Direction.NORTH;
        }
        return Direction.NO_DIR;
    }

    private int getFoodPheromoneDirection(Cell[][] cells){
        int direction = Direction.NO_DIR;
        int north = cells[x][y - 1].getFoodPheromone();
        int south = cells[x][y + 1].getFoodPheromone();
        int west  = cells[x - 1][y].getFoodPheromone();
        int east  = cells[x + 1][y].getFoodPheromone();
        if (north > south && north > west && north > east) direction = Direction.NORTH;
        if (south > north && south > west && south > east) direction = Direction.SOUTH;
        if (west  > north && west  > east && west > south) direction = Direction.WEST;
        if (east  > north && east  > west && east > south) direction = Direction.EAST;
        return direction;
    }

    private int getSearchPheromoneDirection(Cell[][] cells){
        int direction = Direction.NO_DIR;
        int north = cells[x][y - 1].getSearchPheromone();
        int south = cells[x][y + 1].getSearchPheromone();
        int west  = cells[x - 1][y].getSearchPheromone();
        int east  = cells[x + 1][y].getSearchPheromone();
        if (north > south && north > west && north > east) direction = Direction.NORTH;
        if (south > north && south > west && south > east) direction = Direction.SOUTH;
        if (west  > north && west  > east && west > south) direction = Direction.WEST;
        if (east  > north && east  > west && east > south) direction = Direction.EAST;
        return direction;
    }

    public boolean isSearching(){
        return searching;
    }

    public boolean hasFood(){
        return food;
    }

    public int getEnergy(){
        return energy;
    }

    @Override
    public String toString(){
        return "ant[" + this.x + ", " + this.y + "]";
    }

    public void foundFood() {
        this.food = true;
        this.searching = false;
        this.energy += 200;
    }
}
