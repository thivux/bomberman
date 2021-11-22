package oop.bomberman.entities.stilllEntities;

import oop.bomberman.BombermanGame;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.ID;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Brick extends Entity {

    public Brick(int xUnit, int yUnit, ID id) {
        super(xUnit, yUnit, id);
        sprite = Sprite.brick;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getImage(), x, y, BombermanGame.TILES_SIZE, BombermanGame.TILES_SIZE, null);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
