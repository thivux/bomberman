package control;

import entities.Entity;
import gui.GamePanel;

public class Camera {
    private float x, y;
    // size of screen aka camera resolution
    private static final int VIEWPORT_SIZE_X = GamePanel.SCREEN_WIDTH;
    private static final int VIEWPORT_SIZE_Y = GamePanel.SCREEN_HEIGHT;
    // actual size of game
    private static final int WORLD_SIZE_X = GamePanel.TILE_SIZE * 31;
    private static final int WORLD_SIZE_Y = GamePanel.TILE_SIZE * 13;
    // offset
    private static final int offsetMaxX = WORLD_SIZE_X - VIEWPORT_SIZE_X - 48;
    private static final int offsetMaxY = WORLD_SIZE_Y - VIEWPORT_SIZE_Y;
    private static final int offsetMinX = 0;
    private static final int offsetMinY = 0;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(Entity entity) {     // x & y relative to pos of entity
        x += (entity.getX() - x - VIEWPORT_SIZE_X / 2) * 0.05f;
        y += (entity.getY() - y - VIEWPORT_SIZE_Y / 2) * 0.05f;

        if (x > offsetMaxX) x = offsetMaxX;
        else if (x < offsetMinX) x = offsetMinX;
        if (y > offsetMaxY) y = offsetMaxY;
        else if (y < offsetMinY) y = offsetMinY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
