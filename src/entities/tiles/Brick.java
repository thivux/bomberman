package entities.tiles;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;

import java.awt.*;

public class Brick extends Tile {
    public Brick(int x, int y) {
        super(x, y);
        sprite = Sprite.brick;
        id = ID.Brick;
        collision = true;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
