package oop.bomberman;

import oop.bomberman.control.Camera;
import oop.bomberman.control.Handler;
import oop.bomberman.control.Keyboard;
import oop.bomberman.entities.ID;
import oop.bomberman.entities.enemies.LevelLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class BombermanGame extends Canvas implements Runnable {

    public static final int TILES_SIZE = 32,    // size of a tile
            SCREEN_WIDTH = TILES_SIZE * (31 / 2),
            SCREEN_HEIGHT = 13 * TILES_SIZE;

    private boolean isRunning;
    private Thread thread;
    private Handler handler;
    private LevelLoader levelLoader;
    private Camera camera;

    public BombermanGame() {
        new Frame(SCREEN_WIDTH, SCREEN_HEIGHT, "Bomberman", this);

        start();

        handler = new Handler();
        camera = new Camera(0, 0);
        this.addKeyListener(new Keyboard(handler));

        levelLoader = new LevelLoader(handler);
        levelLoader.loadFile();
        levelLoader.createLevel();

    }

    public static void main(String[] args) {
        new BombermanGame();
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;    // number of updates in 1 sec
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    // 60 times per second
    public void update() {
        for (int i = 0; i < handler.movingEntities.size(); i++) {
            if (handler.movingEntities.get(i).getId() == ID.Bomber) {   // camera move according to bomber pos
                camera.update(handler.movingEntities.get(i));
            }
        }

        handler.update();
    }

    // 1000 times per second
    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);   // pre-load 3 frames
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        // background
        g.setColor(Color.red);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // translate, idk why
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.translate(-camera.getX(), -camera.getY());   // begin of cam
        // render both still and moving entities
        handler.render(g);
        graphics2D.translate(-camera.getX(), -camera.getY());     // end of cam

        g.dispose();
        bufferStrategy.show();
    }
}
