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

    public static final int maxLength = 5;
    public final Rectangle rect;
    Random random;

    private int searchPheromone = 1000;
    private boolean searching;
    private boolean food;
    private int previousDirection;

    /** Class constructor
     * @param x location on x axis
     * @param y location on y axis
     */
    public Ant(int x, int y, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        this.rect = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
        this.rect.setFill(Color.ALICEBLUE);
        this.searching = true;
        this.energy = 200;
    }

    public void update(Cell[][] cells){
        cell = cells[this.x][this.y];
        cell.visit(this);

        move(observe(cells));
    }

    private void move(int direction) {
        random = new Random();
        int length = random.nextInt(maxLength);
        direction = random.nextInt(4);
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
                switch(getSearchPheromoneDirection(cells)){
                    case Direction.SOUTH:
                        direction = Direction.NORTH;
                        break;
                    case Direction.NORTH:
                        direction = Direction.SOUTH;
                        break;
                    case Direction.EAST:
                        direction = Direction.WEST;
                        break;
                    case Direction.WEST:
                        direction = Direction.EAST;
                        break;
                }
            } else {
                direction = getFoodPheromoneDirection(cells);
            }
        } else {
            direction = getSearchPheromoneDirection(cells);
        }
        return direction;
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

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getEnergy(){
        return energy;
    }

    @Override
    public String toString(){
        return "ant[" + this.x + ", " + this.y + "]";
    }
}
