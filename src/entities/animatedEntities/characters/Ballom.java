package entities.animatedEntities.characters;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;
import java.util.Random;

public class Ballom extends Characters {
    private int steps;

    public Ballom(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.balloom_left1;
        id = ID.Ballom;
        speed = 1;
        direction = "right";
    }

    public void update() {
        //AI here
        Random random = new Random();
        if (steps <= 0) {
            boolean moved = false;
            while ((canMove("up") || canMove("down") || canMove("left") || canMove("right")) && !moved) {
                switch (random.nextInt(4)) {
                    case 0:
                        if (canMove("up")) {
                            direction = "up";
                            moved = true;
                            break;
                        }
                    case 1:
                        if (canMove("down")) {
                            direction = "down";
                            moved = true;
                            break;
                        }
                    case 2:
                        if (canMove("left")) {
                            direction = "left";
                            moved = true;
                            break;
                        }
                    case 3:
                        if (canMove("right")) {
                            direction = "right";
                            moved = true;
                            break;
                        }
                    default:
                }
                steps = GamePanel.TILE_SIZE * (random.nextInt(4) + 1) / speed;
            }
        } else {
            switch (direction) {
                case "up":
                    if (canMove("up")) {
                        move("up");
                    }
                    break;
                case "down":
                    if (canMove("down")) {
                        move("down");
                    }
                    break;
                case "left":
                    if (canMove("left")) {
                        move("left");
                    }
                    break;
                case "right":
                    if (canMove("right")) {
                        move("right");
                    }
                    break;
                default:
            }
            steps--;
        }

        animate();
    }

    public void draw(Graphics2D g2) {
        switch (direction) {
            case "up":
            case "left":
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 30);
                break;
            case "down":
            case "right":
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 30);
                break;
            default:
        }
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        //g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
//
//    @Override
//    public void kill() {
//
//    }
}
