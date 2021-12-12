package entities.tiles;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Portal extends Tile {
    private Board board;

    public Portal(int x, int y, Board board) {
        super(x, y);
        id = ID.Portal;
        sprite = Sprite.portal;
        collision = true;
        this.board = board;
    }

    public void update() {
        if (board.movingEntities.size() == 1) {
            collision = false;
            if (bounds.intersects(this.board.getBomber().getBounds())) {
//                System.out.println("win");
                board.nextLevel();
                GamePanel.playSE(7);
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
