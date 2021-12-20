package entities.tiles.powerup;

import entities.LayeredEntity;
import entities.tiles.Tile;
import level.Board;

public abstract class Powerup extends Tile {
    protected LayeredEntity layeredEntity;
    protected Board board;

    public Powerup(int x, int y, Board board) {
        super(x, y);
        this.board = board;
    }

    public LayeredEntity getLayeredEntity() {
        return layeredEntity;
    }

    public void setLayeredEntity(LayeredEntity layeredEntity) {
        this.layeredEntity = layeredEntity;
    }

}
