package level;

import control.Keyboard;
import entities.Entity;
import gui.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    protected GamePanel gamePanel;
    protected Keyboard keyboard;
    LevelLoader levelLoader = new LevelLoader(this);
    public List<Entity> movingEntities = new ArrayList<>();
    public List<Entity> stillEntities = new ArrayList<>();

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        keyboard = gamePanel.getKeyboard();
        levelLoader.loadFile();
        levelLoader.createLevel();
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
}
