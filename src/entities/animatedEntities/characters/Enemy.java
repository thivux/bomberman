package entities.animatedEntities.characters;

import gui.GamePanel;
import level.Board;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends Character {
    public Enemy(int x, int y, Board board) {
        super(x, y, board);
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    protected void updateAI() {
        if (!alive) {
            afterKill();
        }
        //AI here
        if (Math.abs(board.getBomber().getX() - this.x) < GamePanel.TILE_SIZE * RANGE && Math.abs(board.getBomber().getY() - this.y) < GamePanel.TILE_SIZE * RANGE) {
            if (board.getBomber().getX() > this.x) {
                if (canMovePassTile("right")) {
                    move("right");
                    if (enemyCollision()) {
                        move("left");
                    }
                }
            } else if (board.getBomber().getX() < this.x) {
                if (canMovePassTile("left")) {
                    move("left");
                    if (enemyCollision()) {
                        move("right");
                    }
                }
            }
            if (board.getBomber().getY() > this.y) {
                if (canMovePassTile("down")) {
                    move("down");
                    if (enemyCollision()) {
                        move("up");
                    }
                }
            } else if (board.getBomber().getY() < this.y) {
                if (canMovePassTile("up")) {
                    move("up");
                    if (enemyCollision()) {
                        move("down");
                    }
                }
            }
        } else {
            moveRandom();
        }
        animate();
    }

    protected void updateNoAI() {
        if (!alive) {
            afterKill();
        }

        moveRandom();

        animate();
    }

    private void moveRandom() {
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
    }
}
