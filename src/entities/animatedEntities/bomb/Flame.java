package entities.animatedEntities.bomb;

import entities.Entity;
import entities.animatedEntities.AnimatedEntity;
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
                if (!last) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_top_last2;
                }
                break;
            case "right":
                if (!last) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_right_last2;
                }
                break;
            case "down":
                if (!last) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_down_last2;
                }
                break;
            case "left":
                if (!last) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_left_last2;
                }
                break;
        }
    }

    @Override
    public void update() {
        collide();
    }

    public void collide() {
        for (int i = 0; i < board.movingEntities.size(); i++) {
            Entity that = board.movingEntities.get(i);
            if (this.getBounds().intersects(that.getBounds())) {
                if (that instanceof Bomb) { // case 1: flame vs bomb
                    Bomb bomb = (Bomb) that;
                    if (bomb.timeToExplode > 0) bomb.explode();
                } else {    // case 2: flame vs characters
                    ((AnimatedEntity) that).kill();
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
