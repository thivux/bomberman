package oop.bomberman.entities.enemies;

import oop.bomberman.control.Handler;
import oop.bomberman.entities.AnimatedEntity;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.ID;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Ballom extends AnimatedEntity {
    Handler handler;

    public Ballom(int xUnit, int yUnit, ID id, Handler handler) {
        super(xUnit, yUnit, id);
        this.sprite = Sprite.balloom_left1;
        this.handler = handler;
    }

    @Override
    public void render(Graphics graphics) { // TODO: change 30
        graphics.drawImage(sprite.getImage(), x, y, 30, 30, null);
    }

    @Override
    public void update() {

    }


    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
