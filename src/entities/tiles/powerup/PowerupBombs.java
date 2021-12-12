package entities.tiles.powerup;

import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class PowerupBombs extends Powerup {
    public PowerupBombs(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.powerup_bombs;
        collision = false;
    }

    public void update() {
        if (layeredEntity.getTopEntity() == this && this.bounds.intersects(this.board.getBomber().getBounds())) {
            this.isRemoved = true;
            //do sth
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
