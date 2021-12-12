package entities.tiles.powerup;

import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class PowerupFlames extends Powerup {
    public PowerupFlames(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.powerup_flames;
        collision = false;
    }

    public void update() {
        if (this.bounds.intersects(this.board.getBomber().getBounds())) {
            this.board.removeStillEntity(this);
            //do sth
            GamePanel.setBombRadius(2);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
