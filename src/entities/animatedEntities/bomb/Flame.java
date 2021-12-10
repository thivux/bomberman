package entities.animatedEntities.bomb;

import entities.Entity;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Flame extends Entity {
    String direction;
    Board board;

    public Flame(int x, int y, String direction, boolean last, Board board) {
        super(x, y);
        this.board = board;
        this.direction = direction;

        switch (direction) {
            case "up":
                if (last == false) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_top_last2;
                }
                break;
            case "right":
                if (last == false) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_right_last2;
                }
                break;
            case "down":
                if (last == false) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_down_last2;
                }
                break;
            case "left":
                if (last == false) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_left_last2;
                }
                break;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
