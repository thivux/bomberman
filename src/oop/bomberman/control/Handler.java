package oop.bomberman.control;

import oop.bomberman.entities.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    public List<Entity> movingEntities = new ArrayList<>();
    public List<Entity> stillEntities = new ArrayList<>();

    public boolean up, down, left, right;   // auto false

    // only update moving entities
    public void update() {
        movingEntities.forEach(Entity::update);
    }


    public void render(Graphics g) {

        for (Entity entity : movingEntities) {
            entity.render(g);
        }

        for (Entity entity : stillEntities) {
            entity.render(g);
        }
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public void addMovingEntity(Entity entity) {
        movingEntities.add(entity);
    }
}
