package entities.tiles.powerup;

import entities.tiles.Tile;
import level.Board;

import java.awt.*;

public class Powerup extends Tile {
    protected Board board;
    
    public Powerup(int x, int y, Board board) {
        super(x, y);
        this.board = board;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

    }
}
