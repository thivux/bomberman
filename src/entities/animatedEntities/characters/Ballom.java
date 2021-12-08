package entities.animatedEntities.characters;

import entities.ID;
import graphics.Sprite;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class Ballom extends Characters {
    public Ballom(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.balloom_left1;
        id = ID.Ballom;
        speed = 1;
        bounds = new Rectangle(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        direction = "right";
    }

    public void update() {
        //AI here
//        x -= speed;
//        bounds.x -= speed;
        animate();
    }

    public void draw(Graphics2D g2) {
        switch (direction) {
            case "up":
            case "down":
                break;
            case "left":
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 30);
                break;
            case "right":
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 30);
                break;
            default:
        }
        g2.drawImage(sprite.getImage(), x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        //g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

}
