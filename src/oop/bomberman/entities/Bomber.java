package oop.bomberman.entities;

import oop.bomberman.control.Handler;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomber extends AnimatedEntity {
    // chi co bomber can handler
    Handler handler;
    private static final int VELOCITY = 5;

    public Bomber(int xUnit, int yUnit, ID id, Handler handler) {
        super(xUnit, yUnit, id);
        this.handler = handler;
        sprite = Sprite.player_down; //sprite ban dau
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getImage(), x, y, 30, 30, null);
    }

    @Override
    public void update() {
        x += dX;
        y += dY;

        if (handler.isDown()) {  // down key is pressed
            sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
            dY = VELOCITY;
        } else if (!handler.isUp()) { // down key is released and up key is not pressed
            dY = 0;
        }

        if (handler.isUp()) { // up
            sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
            dY = -VELOCITY;
        } else if (!handler.isDown()) { // up key is released and down key is not pressed
            dY = 0;
        }

        if (handler.isLeft()) { // left
            sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
            dX = -VELOCITY;
        } else if (!handler.isRight()) { // left key is released and right key is not pressed
            dX = 0;
        }

        if (handler.isRight()) { // right
            sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
            dX = VELOCITY;
        } else if (!handler.isLeft()) { // right key is released and left key is not pressed
            dX = 0;
        }

        animate();
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
