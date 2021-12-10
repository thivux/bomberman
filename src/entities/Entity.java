package entities;

import graphics.Sprite;
import gui.GamePanel;

import java.awt.*;

public abstract class Entity {
    public boolean collision = false;
    protected int x, y;
    protected Sprite sprite;
    protected ID id;
    protected Rectangle bounds;
    protected boolean isRemoved = false;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public Rectangle getBounds() {
        return bounds;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getId() {
        return id;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void remove() {
        isRemoved = true;
    }
}
