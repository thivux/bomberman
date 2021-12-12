package entities.tiles;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;

import java.awt.*;

public class Brick extends Tile {
    private boolean exploded = false;
    private int afterExplosion = 20;
    private int _animate = 0;

    public Brick(int x, int y) {
        super(x, y);
        sprite = Sprite.brick;
        id = ID.Brick;
        collision = true;
    }

    public void update() {
        if (exploded) {
            //System.out.println("here");
            if (afterExplosion > 0) {
                afterExplosion--;
            } else {
                isRemoved = true;
            }
        }
        animate();
    }

    public void draw(Graphics2D g2) {
        if (!exploded) {
            sprite = Sprite.brick;
        } else {
            sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, _animate, 30);
        }
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    @Override
    public void remove() {
        exploded = true;
    }

    private void animate() {
        if (_animate < 60) _animate++;
        else _animate = 0;
    }
}
