package entities.animatedEntities.bomb;

import entities.Entity;
import gui.GamePanel;
import level.Board;

import java.awt.*;

public class DirectionalFlame extends Entity {
    private Board board;
    private int radius;
    private String direction;
    private String[] directions = {"up", "right", "down", "left"};
    private Flame[] flames;
    private int xTile;
    private int yTile;

    public DirectionalFlame(int x, int y, int direction, int bombRadius, Board board) {
        super(x, y);

        this.direction = directions[direction];
        this.radius = bombRadius;
        this.board = board;

        flames = new Flame[realRadius()];
        createDirectionalFlame();
    }

    private void createDirectionalFlame() {
        boolean last;
        int xx = (x + GamePanel.TILE_SIZE / 2) / GamePanel.TILE_SIZE;
        int yy = (y + GamePanel.TILE_SIZE / 2) / GamePanel.TILE_SIZE;

        for (int i = 0; i < flames.length; i++) {
            last = (i == flames.length - 1) ? true : false;

            switch (direction) {
                case "up":
                    yy--;
                    break;
                case "right":
                    xx++;
                    break;
                case "down":
                    yy++;
                    break;
                case "left":
                    xx--;
                    break;
            }
            flames[i] = new Flame(xx * GamePanel.TILE_SIZE, yy * GamePanel.TILE_SIZE, direction, last, board);
//            board.addStillEntity(flames[i]);
        }
    }

    private int realRadius() {
        int radiusCount = 0;

        int xTile = (x + GamePanel.TILE_SIZE / 2) / GamePanel.TILE_SIZE;
        int yTile = (y + GamePanel.TILE_SIZE / 2) / GamePanel.TILE_SIZE;

//        System.out.println(x + " " + y);
//        System.out.println(xTile + " " + yTile);

        while (radiusCount < radius) {
            switch (direction) {
                case "up":
                    yTile--;
                    break;
                case "right":
                    xTile++;
                    break;
                case "down":
                    yTile++;
                    break;
                case "left":
                    xTile--;
                    break;
                default:
                    System.out.println("you fucked up mate");
                    break;
            }

//            System.out.print(direction + ": " + xTile + " " + yTile + " " + board.getTile(xTile, yTile).getClass() + " ");
            if (board.getTile(xTile, yTile).collision) break;

            radiusCount++;
        }
//        System.out.println(direction + ": " + radiusCount);
        return radiusCount;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        for (int i = 0; i < flames.length; i++) {
            flames[i].draw(g2);
        }
    }
}
