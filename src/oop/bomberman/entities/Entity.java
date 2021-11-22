package oop.bomberman.entities;


import oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Sprite sprite;
    protected boolean isRemoved;

    protected ID id;

    protected float dX;
    protected float dY;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, ID id) {
        x = xUnit;
        y = yUnit;
        this.id = id;
    }

    public abstract void render(Graphics graphics);

    public abstract void update();

    public Rectangle getBounds() {  // TODO: change 30
        return new Rectangle(x, y, 30, 30);
    }

    public abstract boolean collide(Entity entity);


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void remove() {
        isRemoved = true;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public ID getId() {
        return id;
    }
}
