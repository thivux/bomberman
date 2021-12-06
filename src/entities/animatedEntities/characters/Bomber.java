package entities.animatedEntities.characters;

import control.Keyboard;
import entities.Entity;
import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Bomber extends Characters {
    Keyboard keyboard;
    String direction;

    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        this.keyboard = board.getKeyboard();
        id = ID.Bomber;
        speed = 2;
        bounds = new Rectangle(x + GamePanel.TILE_SIZE / 6, y + GamePanel.TILE_SIZE / 3, GamePanel.TILE_SIZE / 3 * 2, GamePanel.TILE_SIZE / 3 * 2);
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
            for (Entity entity : board.stillEntities) {
                if ((entity.getBounds().contains(leftX, topY - speed) || entity.getBounds().contains(rightX, topY - speed)) && entity.getId() != ID.Grass) {
                    y += speed;
                    bounds.y += speed;
                    System.out.println(entity.getId());
                }
            }
        }
        if (keyboard.downPressed) {
            direction = "down";
            y += speed;
            bounds.y += speed;
            for (Entity entity : board.stillEntities) {
                if ((entity.getBounds().contains(leftX, bottomY + speed) || entity.getBounds().contains(rightX, bottomY + speed)) && entity.getId() != ID.Grass) {
                    y -= speed;
                    bounds.y -= speed;
                    System.out.println(entity.getId());
                }
            }
        }
        if (keyboard.leftPressed) {
            direction = "left";
            x -= speed;
            bounds.x -= speed;
            for (Entity entity : board.stillEntities) {
                if ((entity.getBounds().contains(leftX - speed, topY) || entity.getBounds().contains(leftX - speed, bottomY)) && entity.getId() != ID.Grass) {
                    x += speed;
                    bounds.x += speed;
                    System.out.println(entity.getId());
                }
            }
        }
        if (keyboard.rightPressed) {
            direction = "right";
            x += speed;
            bounds.x += speed;
            for (Entity entity : board.stillEntities) {
                if ((entity.getBounds().contains(rightX + speed, topY) || entity.getBounds().contains(rightX + speed, bottomY)) && entity.getId() != ID.Grass) {
                    x -= speed;
                    bounds.x -= speed;
                    System.out.println(entity.getId());
                }
            }
        }


//        System.out.println(bounds.x + " " + bounds.y + " " + bounds.width + " " + bounds.height);

        if (keyboard.upPressed || keyboard.downPressed || keyboard.leftPressed || keyboard.rightPressed) {
            animate();
        }

    }

    public void draw(Graphics2D g2) {
        switch (direction) {
            case "up" -> sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, 30);
            case "down" -> sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, 30);
            case "left" -> sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, 30);
            case "right" -> sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, 30);
        }

        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        //g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
