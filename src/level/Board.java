package level;

import control.Keyboard;
import entities.Entity;
import entities.tiles.Tile;
import gui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private GamePanel gamePanel;
    private Keyboard keyboard;

    private LevelLoader levelLoader = new LevelLoader(this);
    private Tile[][] tiles;

    public List<Entity> movingEntities = new ArrayList<>();
    public List<Entity> stillEntities = new ArrayList<>();

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        keyboard = gamePanel.getKeyboard();
        levelLoader.loadFile();
        tiles = new Tile[levelLoader.getHeight()][levelLoader.getWidth()];
        levelLoader.createLevel();

//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles[0].length; j++) {
//                System.out.print(tiles[i][j].getId() + " ");
//            }
//            System.out.println();
//        }
    }

    public void update() {
        for (Entity entity : movingEntities) {
            entity.update();
        }
    }

    public void draw(Graphics2D g2) {
        for (Entity entity : stillEntities) {
            entity.draw(g2);
        }

        for (Entity entity : movingEntities) {
            entity.draw(g2);
        }
    }

    void addStillEntity(Entity entity) {
        stillEntities.add(entity);
    }

    void addMovingEntity(Entity entity) {
        movingEntities.add(entity);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setTile(int row, int col, Tile tile) {
        tiles[col][row] = tile;
    }

    public Tile getTile(int row, int col) {
        return tiles[col][row];
    }
}
