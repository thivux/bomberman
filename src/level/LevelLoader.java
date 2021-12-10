package level;

import entities.animatedEntities.characters.Ballom;
import entities.animatedEntities.characters.Bomber;
import entities.animatedEntities.characters.Oneal;
import entities.tiles.Brick;
import entities.tiles.Grass;
import entities.tiles.Portal;
import entities.tiles.Wall;
import gui.GamePanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelLoader {
    private int level;
    private int height;
    private int width;
    private char[][] map;
    private Board board;

    public LevelLoader(Board board) {
        this.board = board;
    }

    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("res\\levels\\Level1.txt"));
            String[] option = br.readLine().split(" ");
            level = Integer.parseInt(option[0]);
            height = Integer.parseInt(option[1]);
            width = Integer.parseInt(option[2]);

            map = new char[height][width];
            for (int i = 0; i < height; i++) {
                String line = br.readLine();
                for (int j = 0; j < width; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createLevel() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (map[i][j]) {
                    case '#':
                        Wall wall = new Wall(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(wall);
                        board.setTile(j, i, wall);
                        break;
                    case '*':
                        Brick brick = new Brick(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(brick);
                        board.setTile(j, i, brick);
                        break;
                    case 'x':
                        Portal portal = new Portal(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        board.addStillEntity(portal);
                        board.setTile(j, i, portal);
                        break;
                    case 'p':
                        Grass grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(grass);
                        board.setTile(j, i, grass);
                        Bomber bomber = new Bomber(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, board);
                        board.addMovingEntity(bomber);
                        board.setBomber(bomber);
                        break;
                    case '1':
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(grass);
                        board.setTile(j, i, grass);
                        board.addMovingEntity(new Ballom(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, board));
                        break;
                    case '2':
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(grass);
                        board.setTile(j, i, grass);
                        board.addMovingEntity(new Oneal(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, board));
                        break;
                    case 'b': //TODO: bomb item
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(grass);
                        board.setTile(j, i, grass);
                        break;
                    case 'f': //TODO: fire item
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(grass);
                        board.setTile(j, i, grass);
                        break;
                    case 's': //TODO: speed item
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(grass);
                        board.setTile(j, i, grass);
                        break;
                    default:
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        board.addStillEntity(grass);
                        board.setTile(j, i, grass);
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
