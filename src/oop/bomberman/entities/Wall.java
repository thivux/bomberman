package oop.bomberman.entities;

import oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Wall extends Entity {

    public Wall(int xUnit, int yUnit, ID id) {
        super(xUnit, yUnit, id);
        sprite = Sprite.wall;
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
        return new Rectangle(x, y, 30, 30);
    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
