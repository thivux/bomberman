package entities;


import gui.GamePanel;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;


public class LayeredEntity extends Entity {
    protected LinkedList<Entity> entities = new LinkedList<>();

    public LayeredEntity(int x, int y, Entity... entities) {
        super(x, y);
        Collections.addAll(this.entities, entities);
        id = getTopEntity().id;
        collision = getTopEntity().collision;
    }

    @Override
    public void update() {
        clearRemoved();
        getTopEntity().update();
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Entity entity : entities) {
            g2.drawImage(entity.sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        }
    }

    public Entity getTopEntity() {
        return entities.getLast();
    }

    private void clearRemoved() {
        Entity top = getTopEntity();

        if (top.isRemoved()) {
            entities.removeLast();
        }
    }
}
