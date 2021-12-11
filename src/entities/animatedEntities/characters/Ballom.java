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
        if (!alive) {
            afterKill();
        }
        //AI here
        Random random = new Random();
        if (steps <= 0) {
            boolean moved = false;
            while ((canMovePassTile("up") || canMovePassTile("down") || canMovePassTile("left") || canMovePassTile("right")) && !moved) {
                switch (random.nextInt(4)) {
                    case 0:
                        if (canMovePassTile("up")) {
                            direction = "up";
                            moved = true;
                            break;
                        }
                    case 1:
                        if (canMovePassTile("down")) {
                            direction = "down";
                            moved = true;
                            break;
                        }
                    case 2:
                        if (canMovePassTile("left")) {
                            direction = "left";
                            moved = true;
                            break;
                        }
                    case 3:
                        if (canMovePassTile("right")) {
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
                    if (canMovePassTile("up")) {
                        move("up");
                        if (enemyCollision()) {
                            move("down");
                        }
                    }
                    break;
                case "down":
                    if (canMovePassTile("down")) {
                        move("down");
                        if (enemyCollision()) {
                            move("up");
                        }
                    }
                    break;
                case "left":
                    if (canMovePassTile("left")) {
                        move("left");
                        if (enemyCollision()) {
                            move("right");
                        }
                    }
                    break;
                case "right":
                    if (canMovePassTile("right")) {
                        move("right");
                        if (enemyCollision()) {
                            move("left");
                        }
                    }
                    break;
                default:
            }
            steps--;
        }

        animate();
    }

    public void draw(Graphics2D g2) {
        if (alive) {
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
        } else {
            if (afterKill > 0) {
                sprite = Sprite.balloom_dead;
                _animate = 0;
            } else { // finalAnimation
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }
        }
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
