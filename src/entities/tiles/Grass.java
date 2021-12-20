package entities.tiles;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;

import java.awt.*;

public class Grass extends Tile {
    public Grass(int x, int y) {
        super(x, y);
        id = ID.Grass;
        sprite = Sprite.grass;
        collision = false;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
