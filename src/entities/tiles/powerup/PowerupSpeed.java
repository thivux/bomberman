package entities.tiles.powerup;

import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class PowerupSpeed extends Powerup {
    public PowerupSpeed(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.powerup_speed;
        collision = false;
    }

    public void update() {
        if (layeredEntity.getTopEntity() == this && this.bounds.intersects(this.board.getBomber().getBounds())) {
            this.isRemoved = true;
            this.board.getBomber().setSpeed(3);
            GamePanel.playSE(4);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
