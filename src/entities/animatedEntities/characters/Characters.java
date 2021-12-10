package entities.animatedEntities.characters;

import entities.Entity;
import entities.animatedEntities.AnimatedEntity;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public abstract class Characters extends AnimatedEntity {
    protected Board board;
    protected int speed;
    protected String direction;
    protected boolean alive;
    protected int afterKill = 20;

    public Characters(int x, int y, Board board) {
        super(x, y);
        this.board = board;
        alive = true;
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public boolean canMove(String direction) {
        int leftX = bounds.x;
        int rightX = leftX + bounds.width - 1;
        int topY = bounds.y;
        int bottomY = topY + bounds.height - 1;
        switch (direction) {
            case "right":
                return !(board.getTile((rightX + speed) / GamePanel.TILE_SIZE, topY / GamePanel.TILE_SIZE).collision
                        || board.getTile((rightX + speed) / GamePanel.TILE_SIZE, bottomY / GamePanel.TILE_SIZE).collision);
            case "left":
                return !(board.getTile((leftX - speed) / GamePanel.TILE_SIZE, topY / GamePanel.TILE_SIZE).collision
                        || board.getTile((leftX - speed) / GamePanel.TILE_SIZE, bottomY / GamePanel.TILE_SIZE).collision);
            case "down":
                return !(board.getTile(leftX / GamePanel.TILE_SIZE, (bottomY + speed) / GamePanel.TILE_SIZE).collision
                        || board.getTile(rightX / GamePanel.TILE_SIZE, (bottomY + speed) / GamePanel.TILE_SIZE).collision);
            case "up":
                return !(board.getTile(leftX / GamePanel.TILE_SIZE, (topY - speed) / GamePanel.TILE_SIZE).collision
                        || board.getTile(rightX / GamePanel.TILE_SIZE, (topY - speed) / GamePanel.TILE_SIZE).collision);
            default:
                return false;
        }
    }

    public void move(String direction) {
        switch (direction) {
            case "right":
                x += speed;
                bounds.x += speed;
                break;
            case "left":
                x -= speed;
                bounds.x -= speed;
                break;
            case "down":
                y += speed;
                bounds.y += speed;
                break;
            case "up":
                y -= speed;
                bounds.y -= speed;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    //    public abstract void kill();
    protected void kill() {
        alive = false;
    }

    protected void afterKill() {
        if (afterKill > 0) {
            afterKill--;
        } else {
            remove();
        }
    }
}
