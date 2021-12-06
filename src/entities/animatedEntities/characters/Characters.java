package entities.animatedEntities.characters;

import entities.animatedEntities.AnimatedEntity;
import level.Board;

import java.awt.*;

public abstract class Characters extends AnimatedEntity {
    protected Board board;
    protected int speed;

    public Characters(int x, int y, Board board) {
        super(x, y);
        this.board = board;
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);

}
