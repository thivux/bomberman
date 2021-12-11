package gui;

import control.Camera;
import control.Keyboard;
import entities.Entity;
import entities.ID;
import level.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    public final static int ORIGINAL_TILE_SIZE = 16; // 16X16 tile

    public final static int SCALE = 3;
    public final static int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile
    public final static int MAX_SCREEN_COL = 16; // 16 columns
    public final static int MAX_SCREEN_ROW = 12; // 12 rows

    public final static int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public final static int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    // configs
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double SPEED = 1.0;

    private static int bombRate = BOMBRATE;
    private static int bombRadius = BOMBRADIUS;
    private static double speed = SPEED;


    //FPS
    public final static int FPS = 60;

    private Keyboard keyboard = new Keyboard();
    private Thread thread;
    private Board board = new Board(this);
    private Camera camera = new Camera(0, 0);

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        setFocusable(true);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS; // 0.016666 seconds
        double delta = 0;
        long prevTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (thread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - prevTime) / drawInterval;
            timer += (currentTime - prevTime);

            prevTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        for (int i = 0; i < board.movingEntities.size(); i++) {
            Entity entity = board.movingEntities.get(i);
            if (entity.getId() == ID.Bomber) {   // camera move according to bomber pos
                camera.update(entity);
            }
            if (entity.isRemoved()) {
                board.removeMovingEntity(entity);
            }
        }
        board.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.translate(-camera.getX(), -camera.getY());
        board.draw(g2);

        g2.dispose();
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void setBombRadius(int bombRadius) {
        GamePanel.bombRadius = bombRadius;
    }

    public static double getSpeed() {
        return speed;
    }
}
