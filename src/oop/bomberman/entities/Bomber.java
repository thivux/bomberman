package oop.bomberman.entities;

import oop.bomberman.control.Handler;

import java.awt.*;

public class Bomber extends Entity {
    // chi co bomber can handler
     Handler handler;

    public Bomber(int xUnit, int yUnit, ID id, Handler handler) {
        super(xUnit, yUnit, id);
        this.handler = handler;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect(x, y, 30, 30);
    }

    @Override
    public void update() {
        x += dX;
        y += dY;

        if (handler.isDown()) dY = 5;  // down key is pressed
        else if (!handler.isUp()) dY = 0;   // down key is released and up key is not pressed

        if (handler.isUp()) dY = -5;  // up
        else if (!handler.isDown()) dY = 0;   // up key is released and down key is not pressed

        if (handler.isLeft()) dX = -5;  // left
        else if (!handler.isRight()) dX = 0;   // left key is released and right key is not pressed

        if (handler.isRight()) dX = 5;  // right
        else if (!handler.isLeft()) dX = 0;   // right key is released and left key is not pressed
    }

    // used for collision in the future
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
