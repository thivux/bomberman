package entities.animatedEntities.bomb;

import entities.animatedEntities.AnimatedEntity;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Bomb extends AnimatedEntity {
    Board board;
    private int timeToExplode = 120;
    private boolean exploded = false;
    private int afterExplosion = 20;
    private DirectionalFlame[] DirectionalFlames;

    public Bomb(int x, int y, Board board) {
        super(x, y);
        this.sprite = Sprite.bomb;
        this.board = board;
    }

    @Override
    public void update() {
        if (timeToExplode > 0) {
            timeToExplode--;
        } else {    // time out
            if (!exploded) {
                exploded = true;
                displayExplosion();
            } else {
                if (afterExplosion > 0) {
                    afterExplosion--;
                } else {
                    remove();
                }
            }
        }

        animate();
    }

    private void displayExplosion() {
        DirectionalFlames = new DirectionalFlame[4];

        for (int i = 0; i < 4; i++) {
            DirectionalFlames[i] = new DirectionalFlame(x, y, i, GamePanel.getBombRadius(), board);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (!exploded) {    // draw bomb
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
        } else {            // draw flame
            sprite =  Sprite.bomb_exploded2;
            for (int i = 0; i < DirectionalFlames.length; i++) {
                DirectionalFlames[i].draw(g2);
            }
        }

        g2.drawImage(sprite.getImage(),x ,y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
