package ee.soolep.ants;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        initialize();
        mainLoop();

        stage.setTitle("ants");
        stage.setScene(scene);
        stage.show();
    }

    private void mainLoop() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.02), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateCells();
                for (Ant ant : ants) ant.update(cells);
                nest.update();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void updateCells() {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                cells[i][j].update();
            }
        }
    }

    private void initialize() {
        random = new Random();
        root = new Group();
        makeCells();
        nest = new Nest((int) width / 2, (int) height / 2, cellSize);
        makeAnts();
        scene = new Scene(root, width * cellSize, height * cellSize, Color.BLACK);
    }

    private void makeAnts() {
        random.setSeed(System.nanoTime());
        ants = new LinkedList<Ant>();
        for (int i = 0; i < numAnts; i++) {
            Ant ant = new Ant(width / 2, height / 2, cellSize);
            System.out.println(ant);
            ants.add(ant);
            root.getChildren().add(ant.rect);
        }
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
        launch(args);
    }

}
