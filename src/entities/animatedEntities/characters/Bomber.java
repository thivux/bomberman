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
    private int numberOfBombs = 0;
    private int timeBetweenBombs = 0; // no bomb when start
    private int timeBetweenDeath = 0;

    private int timeToPlaySELeft = 0;
    private int timeToPlaySERight = 0;
    private int timeToPlaySEUp = 0;
    private int timeToPlaySEDown = 0;
    private int timeToPlaySE = 15;

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
        if (timeBetweenBombs > 0) {     // bomb dropped
            timeBetweenBombs--;
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
                if (timeToPlaySEUp > 0) {
                    timeToPlaySEUp--;
                } else {
                    GamePanel.playSE(5);
                    timeToPlaySEUp = timeToPlaySE;
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
                if (timeToPlaySEDown > 0) {
                    timeToPlaySEDown--;
                } else {
                    GamePanel.playSE(5);
                    timeToPlaySEDown = timeToPlaySE;
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
                if (timeToPlaySELeft > 0) {
                    timeToPlaySELeft--;
                } else {
                    GamePanel.playSE(6);
                    timeToPlaySELeft = timeToPlaySE;
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
                if (timeToPlaySERight > 0) {
                    timeToPlaySERight--;
                } else {
                    GamePanel.playSE(6);
                    timeToPlaySERight = timeToPlaySE;
                }
            }
        }


        if (keyboard.upPressed || keyboard.downPressed || keyboard.leftPressed || keyboard.rightPressed) {
            animate();
        }

        if (keyboard.spacePressed) {
            if (numberOfBombs < GamePanel.getBombRate() && timeBetweenBombs <= 0) {
                timeBetweenBombs = 30;
                int xt = ((x + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE;
                int yt = ((y + sprite.getSize() / 2) / GamePanel.TILE_SIZE) * GamePanel.TILE_SIZE;
                Bomb bomb = new Bomb(xt, yt, board);
                GamePanel.playSE(1);
                board.addMovingEntity(bomb);
                numberOfBombs++;
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
//                    System.out.println("bomb problem");
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
//        g2.draw(bounds);

    }

    @Override
    public void kill() {
        if (timeBetweenDeath > 0) return;
        super.kill();
        GamePanel.decreaseLives();
        timeBetweenDeath = 120;
    }

    public void afterKill() {
        if (afterKill > 0) {
            afterKill--;
        } else {
            if (finalAnimation > 0)
                finalAnimation--;
            else {
                remove();
                if (GamePanel.getLives() > 0) {
                    board.restartLevel();
                } else {
                    GamePanel.playSE(8);
                }
            }
        }
    }

    public void setSpeed(int i) {
        speed = i;
    }

    public void bombRemoved() {
        numberOfBombs--;
//        System.out.println("bomb removed");
    }

    public void addSpeed() {
        speed++;
    }
}
