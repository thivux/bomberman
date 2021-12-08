package entities.tiles;

import entities.Entity;

public abstract class Tile extends Entity {
    public boolean collision = false;

    public Tile(int x, int y) {
        super(x, y);
    }
}
