package ee.soolep.ants;

public class Cell {

    private int x;
    private int y;
    public static final int MIN_PHEROMONE = 3;
    public static final int MAX_PHEROMONE = 200;
    private int pheromone;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.pheromone = 0;
    }

    /** update method
     * subtracts 1 from pheromone
     * limits pheromone to 200
     */
    public void update(){
        if (this.pheromone > MIN_PHEROMONE) this.pheromone -= 0.1;
        if (this.pheromone > MAX_PHEROMONE) this.pheromone = MAX_PHEROMONE;
    }

    public int getPheromone() {
        return pheromone;
    }

    public void setPheromone(int pheromone) {
        this.pheromone = pheromone;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
