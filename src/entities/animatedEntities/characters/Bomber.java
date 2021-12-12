package entities.animatedEntities.characters;

import control.Keyboard;
import entities.Entity;
import entities.ID;
import entities.animatedEntities.bomb.Bomb;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Bomber extends Characters {
    private final Keyboard keyboard;
    private int lives = 3;

    private int timeBetweenBombsLeft = 0;


    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        this.keyboard = board.getKeyboard();
        id = ID.Bomber;
        speed = 2;
        bounds = new Rectangle(x, y + GamePanel.TILE_SIZE / 10, GamePanel.TILE_SIZE / 3 * 2, GamePanel.TILE_SIZE / 5 * 4);
        direction = "right";
    }

    @Override
    public void update() {

        if (timeBetweenBombsLeft > 0) {
            timeBetweenBombsLeft--;
        }

        if (!alive) {
            afterKill();
        }

        if (keyboard.upPressed) {
            direction = "up";
            if (canMovePassTile("up")) {
                move("up");
                if (!canMovePassMovingEntities()) {
                    move("down");
                }
            }
        }
        if (keyboard.downPressed) {
            direction = "down";
            if (canMovePassTile("down")) {
                move("down");
                if (!canMovePassMovingEntities()) {
                    move("up");
                }
            }
        }
        if (keyboard.leftPressed) {
            direction = "left";
            if (canMovePassTile("left")) {
                move("left");
                if (!canMovePassMovingEntities()) {
                    move("right");
                }
            }
        }
        if (keyboard.rightPressed) {
            direction = "right";
            if (canMovePassTile("right")) {
                move("right");
                if (!canMovePassMovingEntities()) {
                    move("left");
                }
            }
        }


        if (keyboard.upPressed || keyboard.downPressed || keyboard.leftPressed || keyboard.rightPressed) {
            animate();
        }

        if (keyboard.spacePressed) {
            if (timeBetweenBombsLeft > 0) {
//                System.out.println("wait ik");
            } else {
                timeBetweenBombsLeft = GamePanel.timeBetweenBombs;
                int xt = ((x + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE;
                int yt = ((y + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE; //subtract half player height and minus 1 y position
                Bomb bomb = new Bomb(xt, yt, board);
                board.addMovingEntity(bomb);
            }
        }
    }

    public boolean canMovePassMovingEntities() {
        for (int i = 0; i < board.movingEntities.size(); i++) {
            Entity that = board.movingEntities.get(i);
            if (this.getClass() != that.getClass() && this.getBounds().intersects(that.getBounds())) {
                if (!that.getClass().equals(Bomb.class)) {  // collide with enemies
                    kill();
                    return false;
                } else {                                    // collide with bomb
                    return ((Bomb) that).intersectWithBomber;
                }
            }

        }
        return true;
    }

    public boolean canMovePassMovingEntities() {
        for (int i = 0; i < board.movingEntities.size(); i++) {
            Entity that = board.movingEntities.get(i);
            if (this.getClass() != that.getClass() && this.getBounds().intersects(that.getBounds())) {
                if (!that.getClass().equals(Bomb.class)) {  // collide with enemies
                    kill();
                    return false;
                } else {                                    // collide with bomb
                    return ((Bomb) that).intersectWithBomber;
                }
            }
        }
        return true;
    }

    public void draw(Graphics2D g2) {
        if (alive) {

            switch (direction) {
                case "up":
                    sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, 30);
                    break;
                case "down":
                    sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, 30);
                    break;
                case "left":
                    sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, 30);
                    break;
                case "right":
                    sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, 30);
                    break;
                default:
            }
        } else {
            if (afterKill > 0) {
                sprite = Sprite.player_dead1;
                _animate = 0;
            } else { // finalAnimation
                sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, 60);
            }
        }

        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

        // erase comment to see hitbox
        g2.draw(bounds);

    }

    @Override
    public void kill() {
        if (lives > 1) {
            lives--;
        } else
            super.kill();
    }


    public void setSpeed(int i) {
        speed = i;
    }

}
