package entities.animatedEntities.characters;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Ballom extends Enemy {

    public Ballom(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.balloom_left1;
        id = ID.Ballom;
        speed = 1;
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
                    sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 30);
                    break;
                case "down":
                case "right":
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 30);
                    break;
                default:
            }
        } else {
            if (afterKill > 0) {
                sprite = Sprite.balloom_dead;
                _animate = 0;
            } else { // finalAnimation
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }
        }
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
//
//    @Override
//    public void kill() {
//
//    }
}
