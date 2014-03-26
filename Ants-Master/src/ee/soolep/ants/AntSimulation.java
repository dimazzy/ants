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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AntSimulation extends Application {

    public static final int width = 30;
    public static final int height = 30;
    public static final int cellSize = 10;

    List<Ant> ants;
    Iterator<Ant> antIterator;
    Cell[][] cells;
    Nest nest;
    List<Food> foods;
    private int foodCount = 1;
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

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.03), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateCells();
                while (antIterator.hasNext()){
                    Ant ant = antIterator.next();
                    if (ant.getEnergy() < 3){
                        antIterator.remove();
                    }
                    ant.update(cells);
                }
                if (ants.size() < numAnts){
                    ants.add(nest.newAnt());
                }
                antIterator = ants.iterator();
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
        nest = new Nest(width / 3, height / 3, cellSize);
        makeAnts();
        scene = new Scene(root, width * cellSize, height * cellSize, Color.BLACK);
        Food food = new Food((width / 3) * 2, (height / 3) * 2, cellSize);
        root.getChildren().add(food.rect);
        cells[food.x][food.y].hasFood = true;
        //makeFood();
        root.getChildren().add(nest.rect);
    }

    private void makeFood() {
        foods = new LinkedList<Food>();
        for (int i = 0; i < foodCount; i++){
            random.setSeed(System.nanoTime());
            Food food = new Food(random.nextInt(width), random.nextInt(height), cellSize);
            foods.add(food);
            root.getChildren().add(food.rect);
            cells[food.x][food.y].hasFood = true;
        }
    }

    private void makeAnts() {
        random.setSeed(System.nanoTime());
        ants = new LinkedList<Ant>();
        for (int i = 0; i < numAnts; i++) {
            Ant ant = new Ant(width / 2, height / 2, cellSize, nest);
            System.out.println(ant);
            ants.add(ant);
        }
        antIterator = ants.iterator();
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
