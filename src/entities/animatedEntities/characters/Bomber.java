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
        if (!alive) {
            afterKill();
        }

        if (keyboard.upPressed) {
            direction = "up";
            if (canMove("up")) {
                move("up");
                collide();

            }
        }
        if (keyboard.downPressed) {
            direction = "down";
            if (canMove("down")) {
                move("down");
                collide();
            }
        }
        if (keyboard.leftPressed) {
            direction = "left";
            if (canMove("left")) {
                move("left");
                collide();

            }
        }
        if (keyboard.rightPressed) {
            direction = "right";
            if (canMove("right")) {
                move("right");
                collide();

            }
        }


        if (keyboard.upPressed || keyboard.downPressed || keyboard.leftPressed || keyboard.rightPressed) {
            animate();
        }

        if (keyboard.spacePressed) {
//            System.out.println("drop bomb");
            int xt = ((x + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE;
            int yt = ((y + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE; //subtract half player height and minus 1 y position
//            System.out.println(xt + " " + yt);
            Bomb bomb = new Bomb(xt, yt, board);
            board.addMovingEntity(bomb);
            board.setTile(xt / GamePanel.TILE_SIZE, yt / GamePanel.TILE_SIZE, bomb);
        }
    }

    public void collide() {
        for (int i = 0; i < board.movingEntities.size(); i++) {
            Entity that = board.movingEntities.get(i);
            if (this.getClass() != that.getClass() && this.getBounds().intersects(that.getBounds())) {
                if (!that.getClass().equals(Bomb.class)) {
                    kill();
                }
            }
        }
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
}
