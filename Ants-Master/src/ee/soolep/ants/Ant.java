package ee.soolep.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ant {

    private int x;
    private int y;
    public final int cellSize;
    private Cell cell;

    public static final int maxLength = 3;
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
    }

    public void update(Cell[][] cells){
        cell = cells[this.x][this.y];
        cell.visit(this);

        move(observe(cells));
    }

    private void move(int direction) {
        int newX = this.x;
        int newY = this.y;
        int dx = 0;
        int dy = 0;
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
    }

    private void checkBorders(int x, int y) {
        if (x < maxLength) this.x = maxLength;
        if (y < maxLength) this.y = maxLength;
        if (x > AntSimulation.width - maxLength) this.x = AntSimulation.width - maxLength;
        if (y > AntSimulation.height - maxLength) this.y = AntSimulation.height - maxLength;
    }

    private int observe(Cell[][] cells) {
        int direction = 0;
        if (searching){
            direction = searchFood(cells);
        } else {
            direction = deliverFood(cells);
        }
        return direction;
    }

    private int deliverFood(Cell[][] cells) {
        return 0;
    }

    private int searchFood(Cell[][] cells) {
        int north = 0;
        int south = 0;
        int west = 0;
        int east = 0;

        return 0;
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

    @Override
    public String toString(){
        return "ant[" + this.x + ", " + this.y + "]";
    }
}
