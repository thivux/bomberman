package oop.bomberman.entities;

import oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Grass extends Entity {

    public Grass(int xUnit, int yUnit, ID id) {
        super(xUnit, yUnit, id);
        sprite = Sprite.grass;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getImage(), x, y, 30, 30, null);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
