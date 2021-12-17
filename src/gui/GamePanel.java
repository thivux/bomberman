package gui;

import control.Camera;
import control.Keyboard;
import entities.Entity;
import entities.ID;
import entities.animatedEntities.bomb.Bomb;
import entities.animatedEntities.characters.Bomber;
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

    private static int level = 1;
    private static int lives = 3;
    private static int score = 0;

    //FPS
    public final static int FPS = 60;

    private Keyboard keyboard = new Keyboard(this);
    private Thread thread;
    private Board board = new Board(this);
    private Camera camera = new Camera(0, 0);

    private static Sound sound = new Sound();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int endState = 3;
    public final int howToPlayState = 4;
    public int commandNum = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        setFocusable(true);
        playMusic(0);
        gameState = titleState;
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
        if (lives == 0) {
            gameState = endState;
        }
        if (gameState == playState) {
            for (int i = 0; i < board.movingEntities.size(); i++) {
                Entity entity = board.movingEntities.get(i);
                if (entity.getId() == ID.Bomber) {   // camera move according to bomber pos
                    camera.update(entity);
                }
                if (entity.isRemoved()) {
                    board.removeMovingEntity(entity);
                    if (!(entity instanceof Bomber) && !(entity instanceof Bomb)) {
                        increaseScore();
                    }
                }
            }
            board.update();
        } else if (gameState == pauseState) {
            //nothing
        }

//        System.out.println(gameState);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            drawTitleScreen(g2);
        } else if (gameState == endState) {
            drawEndScreen(g2);
        } else if (gameState == howToPlayState) {
            drawTitleScreen(g2);
            drawHowToPlayScreen(g2);
        } else {
            g2.translate(-camera.getX(), -camera.getY());
            board.draw(g2);

            if (gameState == pauseState) {
                drawPauseScreen(g2);
            }
        }
        g2.dispose();
    }

    private void drawHowToPlayScreen(Graphics2D g2) {
        int x = TILE_SIZE * 2;
        int y = TILE_SIZE * 2;
        int width = SCREEN_WIDTH - TILE_SIZE * 4;
        int height = TILE_SIZE * 5;

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

        x += TILE_SIZE;
        y += TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        String text = "wasd to move\n" +
                "space to place bomb\n" +
                "p to pause\n";
        for (String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawEndScreen(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String text = "You Died";

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = SCREEN_WIDTH / 2 - length / 2;
        int y = TILE_SIZE * 4;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45F));

        text = "RETRY";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = SCREEN_WIDTH / 2 - length / 2;
        y = TILE_SIZE * 6;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - TILE_SIZE, y);
        }

        text = "QUIT";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = SCREEN_WIDTH / 2 - length / 2;
        y = TILE_SIZE * 8;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - TILE_SIZE, y);
        }
    }

    private void drawTitleScreen(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String text = "Bomberman";

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = SCREEN_WIDTH / 2 - length / 2;
        int y = TILE_SIZE * 4;

        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45F));

        text = "NEW GAME";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = SCREEN_WIDTH / 2 - length / 2;
        y = TILE_SIZE * 6;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - TILE_SIZE, y);
        }

        text = "HOW TO PLAY";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = SCREEN_WIDTH / 2 - length / 2;
        y = TILE_SIZE * 7;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - TILE_SIZE, y);
        }

        text = "QUIT";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = SCREEN_WIDTH / 2 - length / 2;
        y = TILE_SIZE * 8;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - TILE_SIZE, y);
        }

    }

    private void drawPauseScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (int) camera.getX() + SCREEN_WIDTH / 2 - length / 2;
        int y = (int) camera.getY() + SCREEN_HEIGHT / 2;

        g2.drawString(text, x, y);
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

    public static void addBombRadius() {
        bombRadius++;
    }

    public static double getSpeed() {
        return speed;
    }

    public void nextLevel() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    public void increaseScore() {
        score += 100;
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public static int getLives() {
        return lives;
    }

    public static void decreaseLives() {
        lives--;
    }

    public static void addBombRate() {
        bombRate++;
    }

    public void playMusic(int idx) {
        sound.setFile(idx);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public static void playSE(int idx) {
        sound.setFile(idx);
        sound.play();
    }

    public void resetGame() {
        lives = 3;
        score = 0;
        board = new Board(this);
        camera = new Camera(0, 0);
    }
}
