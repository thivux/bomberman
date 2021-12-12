package entities.animatedEntities.bomb;

import entities.Entity;
import entities.animatedEntities.AnimatedEntity;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Bomb extends AnimatedEntity {
    Board board;
    public int timeToExplode = 120;
    private boolean exploded = false;
    private int afterExplosion = 20;
    private DirectionalFlame[] DirectionalFlames;
    public boolean intersectWithBomber = true;

    public Bomb(int x, int y, Board board) {
        super(x, y);
        this.sprite = Sprite.bomb;
        this.board = board;
        collision = true;
//        bounds = new Rectangle(x, y, GamePanel.TILE_SIZE + 2, GamePanel.TILE_SIZE + 2);
    }

    @Override
    public void update() {
        if (intersectWithBomber) {
            if (!this.bounds.intersects(board.getBomber().getBounds())) {
                intersectWithBomber = false;
            }
        }

        if (timeToExplode > 0) {
            timeToExplode--;
        } else {    // time out
            if (!exploded) {

                explode();

            } else {
                if (afterExplosion > 0) {
                    afterExplosion--;
                } else {
                    remove();
                    board.getBomber().bombRemoved();
//                    board.setTile(x / GamePanel.TILE_SIZE, y / GamePanel.TILE_SIZE, new Grass(x, y));
                }
            }
        }

        animate();
    }

    public void explode() {
        exploded = true;
        displayExplosion();
    }

    public void collideWithCharacter() {

        for (int i = 0; i < board.movingEntities.size(); i++) {
            Entity that = board.movingEntities.get(i);
            if (!that.getClass().equals(Bomb.class) && this.getBounds().intersects(that.getBounds())) {
                if (that instanceof AnimatedEntity) {
                    ((AnimatedEntity) that).kill();
                }
            }
        }
    }

    private void displayExplosion() {
        DirectionalFlames = new DirectionalFlame[4];

        for (int i = 0; i < 4; i++) {
            DirectionalFlames[i] = new DirectionalFlame(x, y, i, GamePanel.getBombRadius(), board);
        }


        collideWithCharacter();


        for (int i = 0; i < 4; i++) {
            DirectionalFlames[i].update();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (!exploded) {    // draw bomb
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
        } else if (afterExplosion > 0) {            // draw flame
            sprite = Sprite.bomb_exploded2;
            if (afterExplosion > 0) {
                for (int i = 0; i < DirectionalFlames.length; i++) {
                    DirectionalFlames[i].draw(g2);
                }
            }
        }

        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.draw(bounds);
    }

    @Override
    public void kill() {
    }
}
