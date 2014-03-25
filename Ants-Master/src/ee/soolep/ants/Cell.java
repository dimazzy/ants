package ee.soolep.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {

    public final int x;
    public final int y;
    public final int cellSize;
    public final Rectangle rect;

    public static final int maxPheromone = 200;
    public static final int evaporation = 3;

    private int searchPheromone;

    public Cell(int x, int y, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        this.rect = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
        this.searchPheromone = 200;
    }

    /** update method
     * subtracts evaporation from pheromones
     * limits pheromones to 200
     * sets color
     */
    public void update(){
        if (searchPheromone > maxPheromone) searchPheromone = maxPheromone;
        if (searchPheromone > 3) searchPheromone -= evaporation;
        rect.setFill(Color.rgb(0, searchPheromone, 0));
    }

    /** adds pheromones from visiting ant
     * @param ant that is on the cell
     */
    public void visit(Ant ant){

    }

}
