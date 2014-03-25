package ee.soolep.ants;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class AntSimulation extends Application {

    public static final int width = 100;
    public static final int height = 100;
    public static final int cellSize = 4;

    List<Ant> ants;
    Cell[][] cells;
    Nest nest;
    private int numAnts = 10;
    Random random;
    Group root;

    @Override
    public void start(Stage stage) throws Exception {
        initialize();

    }

    private void initialize() {
        random = new Random();
        root = new Group();
        makeCells();
        nest = new Nest((int) width / 2, (int) height / 2, cellSize);
        makeAnts();
    }

    private void makeAnts() {
        random.setSeed(System.nanoTime());
        ants = new LinkedList<Ant>();
        for (int i = 0; i < numAnts; i++)
            ants.add(new Ant(random.nextInt(width - 4) + 2, random.nextInt(height - 4) + 2));
    }

    private void makeCells() {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Cell cell = new Cell(i, j, cellSize);
                cells[i][j] = cell;
                root.getChildren().add(cell.rect);
            }
        }
    }

    public static void main(String[] args){
        new AntSimulation();
    }

}
