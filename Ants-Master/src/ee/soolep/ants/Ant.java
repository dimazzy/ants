package ee.soolep.ants;

import java.util.Random;

//asdf
public class Ant extends Entity{

    public static final int minLength = 1;
    public static final int maxLength = 2;
    Random rnd;

    /** Class constructor
     * @param x location on x axis
     * @param y location on y axis
     */
    public Ant(int x, int y) {
        super(x, y);
        rnd = new Random();
    }

    /** update method.
     *  ant oobserves the world and moves.
     * @param world
     */
    public void update(World world){
        int direction = observe(world);
        move(direction, world);
    }

    /** move method
     *  random steps (minLength, maxLength) to given direction
     *
     * @param direction
     * @param world
     */
    private void move(int direction, World world) {
        int newX = this.getX();
        int newY = this.getY();
        int length = minLength + (int)(Math.random() * ((maxLength - minLength) + 1));
        switch (direction){
            case (Direction.NORTH):
                newY = this.getY() - length;
                break;
            case (Direction.SOUTH):
                newY = this.getY() + length;
                break;
            case (Direction.WEST):
                newX = this.getX() - length;
                break;
            case (Direction.EAST):
                newX = this.getX() + length;
                break;
            default:
                System.out.println(direction);
                break;
        }
        if (newX < 0) this.setX(0);
        else if (newX > world.width) this.setX(world.width - 1);
        else if (newY < 0) this.setY(0);
        else if (newY > world.height) this.setY(world.height);
        else {
            this.setX(newX); this.setY(newY);
        }
        return;
    }

    /** decides where ant should move
     *  moves where pheromone is higher
     *  or moves randomly
     *
     * @param world
     * @return direction where to move
     */
    private int observe(World world) {
        int direction = 0;
        int left = 0;
        int right = 0;
        int top = 0;
        int bot = 0;
        int x = this.getX();
        int y = this.getY();

        rnd.setSeed(System.nanoTime());
        if (x < 5) this.setX(5);
        if (x > world.width) this.setX(rnd.nextInt(world.width));
        if (y < 5) this.setY(5);
        if (y > world.height) this.setY(rnd.nextInt(world.height));

        try {
            left = world.cells[this.getX() - 1][this.getY()].getPheromone();
            right = world.cells[this.getX() + 1][this.getY()].getPheromone();
            top = world.cells[this.getX()][this.getY() - 1].getPheromone();
            bot = world.cells[this.getX()][this.getY() + 1].getPheromone();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (left > right && left > bot && left > top) direction = Direction.EAST;
        else if (right > top && right > right && left > top) direction = Direction.WEST;
        else if (bot > right && bot > left && left > top) direction = Direction.SOUTH;
        else if (top > right && top > bot && top > left) direction = Direction.NORTH;
        else {
            rnd.setSeed(System.nanoTime());
            direction = rnd.nextInt(5);
        }

        return direction;
    }
}
