package entities.animatedEntities.characters;

import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;
import java.util.Random;

public class Doll extends Characters {
    public Doll(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.doll_right1;
        speed = 4;
        direction = "right";
    }

    public void update() {
        updateNoAI();
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            switch (direction) {
                case "up":
                case "left":
                    sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, _animate, 30);
                    break;
                case "down":
                case "right":
                    sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, _animate, 30);
                    break;
                default:
            }

        } else {
            if(afterKill > 0) {
                sprite = Sprite.doll_dead;
                _animate = 0;
            } else { // final animation
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }
        }

        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
