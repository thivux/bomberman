package level;

import control.Keyboard;
import entities.Entity;

import entities.ID;
import entities.animatedEntities.bomb.Bomb;
import entities.animatedEntities.characters.Bomber;
import entities.tiles.Tile;
import gui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private GamePanel gamePanel;
    private Keyboard keyboard;
    private Bomber bomber;

    private LevelLoader levelLoader = new LevelLoader(this);
    private Entity[][] tiles;

    public List<Entity> movingEntities = new ArrayList<>();
    public List<Entity> stillEntities = new ArrayList<>();

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        keyboard = gamePanel.getKeyboard();
        levelLoader.loadFile();
        tiles = new Entity[levelLoader.getHeight()][levelLoader.getWidth()];
        levelLoader.createLevel();

//        System.out.println(getTile(4, 3).getClass());
//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles[0].length; j++) {
//                System.out.print(tiles[i][j].getId() + " ");
//            }
//            System.out.println();
//        }
    }

    public void update() {
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

    public void addStillEntity(Entity entity) {
        stillEntities.add(entity);
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
}
