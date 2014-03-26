package ee.soolep.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Created by andres on 3/25/14.
 */
public class Nest {

    public final int cellSize;
    public final int x;
    public final int y;
    public final Rectangle rect;

    private int foodCount;
    Random random;

    public Nest(int x, int y, int cellSize) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        random = new Random();
        this.rect = new Rectangle(x, y, cellSize, cellSize);
        this.rect.setFill(Color.AQUAMARINE);
        this.foodCount = 0;
    }

    public int getFoodCount(){
        return foodCount;
    }

    public void update() {
        this.rect.setFill(Color.RED);
    }

    public Ant newAnt(){
        return new Ant(x, y, cellSize);
    }
}

