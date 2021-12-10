package entities.animatedEntities.bomb;

import entities.Entity;
import entities.animatedEntities.AnimatedEntity;
import entities.animatedEntities.characters.Ballom;
import entities.tiles.Grass;
import entities.tiles.Tile;
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
        collision = true;
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
                    board.setTile(x / GamePanel.TILE_SIZE, y / GamePanel.TILE_SIZE, new Grass(x, y));
                }
            }
        }

        animate();
    }

    public void collide() {
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

        collide();

        for (int i = 0; i < 4; i++) {
            DirectionalFlames[i].update();
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
        g2.draw(bounds);
    }

    @Override
    public void kill() {
        exploded = true;
    }
}
