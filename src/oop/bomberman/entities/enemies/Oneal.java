package oop.bomberman.entities.enemies;

import oop.bomberman.control.Handler;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.ID;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Oneal extends Entity {
    Handler handler;

    public Oneal(int xUnit, int yUnit, ID id, Handler handler) {
        super(xUnit, yUnit, id);
        this.sprite = Sprite.oneal_left1;
        this.handler = handler;
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
