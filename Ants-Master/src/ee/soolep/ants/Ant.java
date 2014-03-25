package ee.soolep.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ant {

    private int x;
    private int y;
    public final int cellSize;
    public final Rectangle rect;

    /** Class constructor
     * @param x location on x axis
     * @param y location on y axis
     */
    public Ant(int x, int y, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        this.rect = new Rectangle(x, y, cellSize, cellSize);
        this.rect.setFill(Color.ALICEBLUE);
    }

    public void update(){
    }

}
