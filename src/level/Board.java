package level;

import control.Keyboard;
import entities.Entity;
import entities.animatedEntities.characters.Bomber;
import gui.GamePanel;
import gui.InfoPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private GamePanel gamePanel;
    private Keyboard keyboard;
    private Bomber bomber;
    private int level;

    private LevelLoader levelLoader;
    private Entity[][] tiles;

    public List<Entity> movingEntities;
    public List<Entity> stillEntities;

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.level = gamePanel.getLevel();
        levelLoader = new LevelLoader(this);
        movingEntities = new ArrayList<>();
        stillEntities = new ArrayList<>();
        keyboard = gamePanel.getKeyboard();
        levelLoader.loadFile();
        tiles = new Entity[levelLoader.getHeight()][levelLoader.getWidth()];
        levelLoader.createLevel();
    }

    public void update() {
        InfoPanel.updateLevel();
        InfoPanel.updateLives();
        InfoPanel.updateScores();
        for (int i = 0; i < stillEntities.size(); i++) {
            stillEntities.get(i).update();
        }

        for (int i = 0; i < movingEntities.size(); i++) {
            movingEntities.get(i).update();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < stillEntities.size(); i++) {
            stillEntities.get(i).draw(g2);
        }

        for (int i = 0; i < movingEntities.size(); i++) {
            movingEntities.get(i).draw(g2);
        }
    }

    public void nextLevel() {
        gamePanel.nextLevel();
        level++;
        movingEntities = new ArrayList<>();
        stillEntities = new ArrayList<>();
        levelLoader.loadFile();
        tiles = new Entity[levelLoader.getHeight()][levelLoader.getWidth()];
        levelLoader.createLevel();
    }

    public void addStillEntity(Entity entity) {
        stillEntities.add(entity);
    }

    public void removeStillEntity(Entity entity) {
        stillEntities.remove(entity);
    }

    public void addMovingEntity(Entity entity) {
        movingEntities.add(entity);
    }

    public void removeMovingEntity(Entity entity) {
        movingEntities.remove(entity);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setTile(int row, int col, Entity tile) {
        tiles[col][row] = tile;
    }

    public Entity getTile(int col, int row) {
        return tiles[row][col];
    }

    public Bomber getBomber() {
        return bomber;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public int getLevel() {
        return level;
    }

    public void restartLevel() {
        Bomber bomber = new Bomber(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, this);
        addMovingEntity(bomber);
        GamePanel.playSE(3);
    }
}
