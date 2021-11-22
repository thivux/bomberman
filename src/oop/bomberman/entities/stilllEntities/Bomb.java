package oop.bomberman.entities.stilllEntities;

import oop.bomberman.entities.Entity;
import oop.bomberman.entities.ID;

import java.awt.*;

public class Bomb extends Entity {
    private boolean exploded;

    public Bomb(int xUnit, int yUnit, ID id) {
        super(xUnit, yUnit, id);
    }


    @Override
    public void render(Graphics graphics) {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
