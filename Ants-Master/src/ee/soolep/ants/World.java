package ee.soolep.ants;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel{

    Cell[][] cells;
    int height;
    int width;
    int boxSize;
    int numAnts = 300;
    ArrayList<Ant> ants = new ArrayList<Ant>();

    /** Class constructor overrides JPanel
     * makes cells and ants
     *
     * @param width number of horizontal cells
     * @param height number of vertical cells
     * @param boxSize size of cells (pixel)
     */
    World(int width, int height, int boxSize) {
        super();
        this.height = height;
        this.width = width;
        this.boxSize = boxSize;
        setSize(width, height);
        makeAnts(numAnts);
        makeCells();
    }

    /** initializes cells and sets their pheromone to 3
     *
     */
    private void makeCells(){
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                cells[i][j] = new Cell(i, j);
                cells[i][j].setPheromone(3);
            }
        }
    }

    /** creates ants
     *
     * @param numAnts how many ants are created
     */
    private void makeAnts(int numAnts) {
        for (int i = 0; i < numAnts; i++){
            int x = (int)(4 + (Math.random() * (width - 50)));
            int y = (int)(4 + (Math.random() * (height -50)));
            Ant ant = new Ant(x, y);
            ants.add(ant);
        }
    }

    /** main loop
     * updates cells and ants
     *
     * @param g Graphics object to draw on
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.white);

        for (int i = 0; i < width; i++){
            g.drawLine((int)(i*boxSize), 0, (int)(i*boxSize), (int)(height*boxSize));
        }
        for (int i = 0; i < height; i++){
            g.drawLine(0, (int)(i*boxSize), (int)(width*boxSize), (int)(i*boxSize));
        }

        updateCells();
        drawCells(g);
        for (Ant ant : ants) {
            ant.update(this);
            drawAnt(ant, g);
        }
    }

    /** draws cells based on their phintellij idea generate javadoceromone level
     *
     * @param g
     */
    private void drawCells(Graphics g) {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Cell cell = cells[i][j];
                Color color;
                if (cells[i][j].getPheromone() > 240) color = new Color(0, 255, 0);
                else color = new Color(0, cell.getPheromone(), 0);
                g.setColor(color);
                g.fillRect(cell.getX() * boxSize, cell.getY() * boxSize, boxSize, boxSize);
                g.setColor(Color.black);
            }
        }
    }

    /** updates cells
     * if ant is on cell, adds pheromone to cell
     * calls Cell.update() method
     */
    private void updateCells() {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                cells[i][j].update();
                for (Ant ant : ants){
                    if (ant.getX() == i && ant.getY() == j){
                        cells[i][j].setPheromone(cells[i][j].getPheromone() + 30);
                    }
                }
            }
        }
    }

    /** draws ants
     *
     * @param ant Ant object to draw
     * @param g Graphics object to draw on
     */
    private void drawAnt(Ant ant, Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(ant.getX() * boxSize, ant.getY() * boxSize, boxSize, boxSize);
    }
}
