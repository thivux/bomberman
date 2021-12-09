package entities.animatedEntities.characters;

import control.Keyboard;
import entities.ID;
import entities.animatedEntities.bomb.Bomb;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Bomber extends Characters {
    private Keyboard keyboard;

    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        this.keyboard = board.getKeyboard();
        id = ID.Bomber;
        speed = 2;
        bounds = new Rectangle(x, y + GamePanel.TILE_SIZE / 10, GamePanel.TILE_SIZE / 3 * 2, GamePanel.TILE_SIZE / 3 * 2);
        direction = "right";
    }

    @Override
    public void update() {
        int leftX = bounds.x;
        int rightX = leftX + bounds.width;
        int topY = bounds.y;
        int bottomY = topY + bounds.height;

        if (keyboard.upPressed) {
            direction = "up";
            y -= speed;
            bounds.y -= speed;

            if (board.getTile(leftX / GamePanel.TILE_SIZE, (topY - speed) / GamePanel.TILE_SIZE).collision
                    || board.getTile(rightX / GamePanel.TILE_SIZE, (topY - speed) / GamePanel.TILE_SIZE).collision) {
                y += speed;
                bounds.y += speed;
            }
        }
        if (keyboard.downPressed) {
            direction = "down";
            y += speed;
            bounds.y += speed;

            if (board.getTile(leftX / GamePanel.TILE_SIZE, (bottomY + speed) / GamePanel.TILE_SIZE).collision
                    || board.getTile(rightX / GamePanel.TILE_SIZE, (bottomY + speed) / GamePanel.TILE_SIZE).collision) {
                y -= speed;
                bounds.y -= speed;
            }
        }
        if (keyboard.leftPressed) {
            direction = "left";
            x -= speed;
            bounds.x -= speed;
            if (board.getTile((leftX - speed) / GamePanel.TILE_SIZE, topY / GamePanel.TILE_SIZE).collision
                    || board.getTile((leftX - speed) / GamePanel.TILE_SIZE, bottomY / GamePanel.TILE_SIZE).collision) {
                x += speed;
                bounds.x += speed;
            }
        }
        if (keyboard.rightPressed) {
            direction = "right";
            x += speed;
            bounds.x += speed;
            if (board.getTile((rightX + speed) / GamePanel.TILE_SIZE, topY / GamePanel.TILE_SIZE).collision
                    || board.getTile((rightX + speed) / GamePanel.TILE_SIZE, bottomY / GamePanel.TILE_SIZE).collision) {
                x -= speed;
                bounds.x -= speed;
            }
        }

//        System.out.println(bounds.x / GamePanel.TILE_SIZE + " " + bounds.y / GamePanel.TILE_SIZE);

        if (keyboard.upPressed || keyboard.downPressed || keyboard.leftPressed || keyboard.rightPressed) {
            animate();
        }

        if (keyboard.spacePressed) {
//            System.out.println("drop bomb");
            int xt = ((x + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE;
            int yt = ((y + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE; //subtract half player height and minus 1 y position
            System.out.println(xt + " " + yt);
            Bomb bomb = new Bomb(xt, yt, board);
            board.addMovingEntity(bomb);
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
        //g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g2.draw(bounds);
    }
}
