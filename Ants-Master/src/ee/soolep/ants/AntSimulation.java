package ee.soolep.ants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class AntSimulation extends JFrame implements Runnable{

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    public static final int BOX_SIZE = 4;
    private int width = WIDTH * BOX_SIZE;
    private int height = HEIGHT * BOX_SIZE;

    World world;

    boolean running = false;

    public static void main(String[] args){
        new AntSimulation();
    }

    public AntSimulation() {
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        world = new World(WIDTH, HEIGHT, BOX_SIZE);
        add(world, BorderLayout.CENTER);
        //add(makeMenuBar(), BorderLayout.NORTH);
        setVisible(true);
        Thread t = new Thread(this);
        t.start();
    }


    private JMenuBar makeMenuBar() {
        JMenuBar menubar = new JMenuBar();

        // file menu
        JMenu file = new JMenu("file");

        JMenuItem fileNew = new JMenuItem("new");
        fileNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new file");
            }
        });

        JMenuItem fileExit = new JMenuItem("exit");
        fileExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("exit");
                System.exit(0);
            }
        });
        file.add(fileNew);
        file.add(fileExit);
        menubar.add(file);

        // tempo menu
        JMenu tempo = new JMenu("tempo");
        JMenuItem tempoIncrease = new JMenuItem("increase");
        tempoIncrease.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("increase tempo");
            }
        });
        tempo.add(tempoIncrease);

        JMenuItem tempoDecrease = new JMenuItem("decrease");
        tempoDecrease.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("decrease tempo");
            }
        });
        tempo.add(tempoDecrease);
        menubar.add(tempo);

        JMenuItem start = new JMenuItem("start");
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("start");
            }
        });
        menubar.add(start);

        JMenuItem stop = new JMenuItem("stop");
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stop");
            }
        });
        menubar.add(stop);
        return menubar;
    }

    @Override
    public void run() {
        while(true){
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
