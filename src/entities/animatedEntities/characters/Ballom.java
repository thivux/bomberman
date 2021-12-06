package entities.animatedEntities.characters;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Ballom extends Characters {
    public Ballom(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.balloom_left1;
        id = ID.Ballom;
        bounds = new Rectangle(x + GamePanel.TILE_SIZE / 6, y + GamePanel.TILE_SIZE / 3, GamePanel.TILE_SIZE / 3 * 2, GamePanel.TILE_SIZE / 3 * 2);
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        //g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
