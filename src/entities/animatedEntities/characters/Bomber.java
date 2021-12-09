package entities.animatedEntities.characters;

import control.Keyboard;
import entities.ID;
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
        speed = 3;
        bounds = new Rectangle(x + GamePanel.TILE_SIZE / 6, y + GamePanel.TILE_SIZE / 3, GamePanel.TILE_SIZE / 3 * 2, GamePanel.TILE_SIZE / 3 * 2);
        direction = "right";
    }

    @Override
    public void update() {
        if (keyboard.upPressed) {
            direction = "up";
            if (canMove("up")) {
                move("up");
            }
        }
        if (keyboard.downPressed) {
            direction = "down";
            if (canMove("down")) {
                move("down");
            }
        }
        if (keyboard.leftPressed) {
            direction = "left";
            if (canMove("left")) {
                move("left");
            }
        }
        if (keyboard.rightPressed) {
            direction = "right";
            if (canMove("right")) {
                move("right");
            }
        }

//        System.out.println(bounds.x / GamePanel.TILE_SIZE + " " + bounds.y / GamePanel.TILE_SIZE);

        if (keyboard.upPressed || keyboard.downPressed || keyboard.leftPressed || keyboard.rightPressed) {
            animate();
        }
    }

    public void draw(Graphics2D g2) {
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

        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

        //erase comment to see hitbox
        g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
