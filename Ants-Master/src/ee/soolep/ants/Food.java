package ee.soolep.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by andres on 3/26/14.
 */
public class Food {
    public final int x;
    public final int y;
    public final Rectangle rect;

    public Food(int x, int y, int cellSize){
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
        this.rect.setFill(Color.RED);
    }
}
