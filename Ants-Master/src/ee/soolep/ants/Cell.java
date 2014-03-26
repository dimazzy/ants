package ee.soolep.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {

    public final int x;
    public final int y;
    public final int cellSize;
    public final Rectangle rect;
    public boolean hasFood;

    public static final int maxPheromone = 200;
    public static final double evaporation = 0.1;
    public static final double foodEvaporation = 0.01;

    private int searchPheromone;
    private int foodPheromone;

    public Cell(int x, int y, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        this.rect = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
        this.searchPheromone = 0;
        this.foodPheromone = 0;
    }

    /** update method
     * subtracts evaporation from pheromones
     * limits pheromones to 200
     * sets color
     */
    public void update(){
        if (searchPheromone > maxPheromone) searchPheromone = maxPheromone;
        if (searchPheromone > 3) searchPheromone -= evaporation;
        if (foodPheromone > maxPheromone) foodPheromone = maxPheromone;
        if (foodPheromone > 3) foodPheromone -= foodEvaporation;
        if (hasFood) foodPheromone = 210;
        rect.setFill(Color.rgb(0, (int)searchPheromone, (int)foodPheromone));
    }

    /** adds pheromones from visiting ant
     * @param ant that is on the cell
     */
    public void visit(Ant ant){
        if (ant.isSearching()) {
            this.searchPheromone += ant.getEnergy() / 60;
        }
        if (ant.hasFood()) {
            this.foodPheromone += ant.getEnergy() / 20;
        }
        if (hasFood) ant.foundFood();
    }

    public int getSearchPheromone(){
        return searchPheromone;
    }

    public int getFoodPheromone(){
        return foodPheromone;
    }

}
