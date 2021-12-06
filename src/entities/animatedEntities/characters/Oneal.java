package entities.animatedEntities.characters;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Oneal extends Characters {
    public Oneal(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.oneal_left1;
        id = ID.Oneal;
        bounds = new Rectangle(x + GamePanel.TILE_SIZE / 6, y + GamePanel.TILE_SIZE / 3, GamePanel.TILE_SIZE / 3 * 2, GamePanel.TILE_SIZE / 3 * 2);
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        //g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
