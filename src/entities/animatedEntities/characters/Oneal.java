package entities.animatedEntities.characters;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;
import java.util.Random;

public class Oneal extends Characters {
    public static final int RANGE = 4;
    private int steps;

    public Oneal(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.oneal_left1;
        id = ID.Oneal;
        speed = 2;
        bounds = new Rectangle(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        direction = "right";
    }

    public void update() {
        //AI here
        if (Math.abs(board.getBomber().getX() - this.x) < GamePanel.TILE_SIZE * RANGE && Math.abs(board.getBomber().getY() - this.y) < GamePanel.TILE_SIZE * RANGE) {
            if (board.getBomber().getX() > this.x) {
                if (canMove("right")) {
                    move("right");
                }
            } else if (board.getBomber().getX() < this.x) {
                if (canMove("left")) {
                    move("left");
                }
            }
            if (board.getBomber().getY() > this.y) {
                if (canMove("down")) {
                    move("down");
                }
            } else if (board.getBomber().getY() < this.y) {
                if (canMove("up")) {
                    move("up");
                }
            }
        } else {
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
        }
        animate();
    }

    public void draw(Graphics2D g2) {
        switch (direction) {
            case "up":
            case "left":
                sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 30);
                break;
            case "down":
            case "right":
                sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 30);
                break;
            default:
        }
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        //g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
