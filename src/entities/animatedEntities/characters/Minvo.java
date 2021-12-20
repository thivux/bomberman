package entities.animatedEntities.characters;

import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Minvo extends Characters {

    public Minvo(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.minvo_left1;
        speed = 4;
        direction = "right";
    }

    public void update() {
        updateAI();
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            switch (direction) {
                case "up":
                case "left":
                    sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, _animate, 30);
                    break;
                case "down":
                case "right":
                    sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, _animate, 30);
                    break;
                default:
            }

        } else {
            if(afterKill > 0) {
                sprite = Sprite.minvo_dead;
                _animate = 0;
            } else { // final animation
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }
        }

        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
