package oop.bomberman;

import oop.bomberman.control.Handler;
import oop.bomberman.control.Keyboard;
import oop.bomberman.entities.Bomber;
import oop.bomberman.entities.ID;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class BombermanGame extends Canvas implements Runnable {

    public static final int TILES_SIZE = 32,    // size of a tile
            WIDTH = TILES_SIZE * (31 / 2),
            HEIGHT = 13 * TILES_SIZE;

    private boolean isRunning;
    private Thread thread;
    private Handler handler;

    public BombermanGame() {
        new Frame(WIDTH, HEIGHT, "Bomberman", this);

        start();

        handler = new Handler();
        this.addKeyListener(new Keyboard(handler));

        handler.addMovingEntity(new Bomber(0, 0, ID.Bomber, handler));
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
        double amountOfTicks = 60.0;    // number of updates in 1 sec
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

//    public void createMap() {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                } else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }
//    }

    // 60 times per second
    public void update() {
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
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // render both still and moving entities
        handler.render(g);

        g.dispose();
        bufferStrategy.show();
    }
}
