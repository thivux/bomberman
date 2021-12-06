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
    char[][] map;
    Board board;

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
                        board.addStillEntity(new Wall(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        break;
                    case '*':
                        board.addStillEntity(new Brick(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        break;
                    case 'x':
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        board.addStillEntity(new Portal(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        break;
                    case 'p':
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        board.addMovingEntity(new Bomber(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, board));
                        break;
                    case '1':
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        board.addMovingEntity(new Ballom(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, board));
                        break;
                    case '2':
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        board.addMovingEntity(new Oneal(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, board));
                        break;
                    case 'b': //TODO: bomb item
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        break;
                    case 'f': //TODO: fire item
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        break;
                    case 's': //TODO: speed item
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                        break;
                    default:
                        board.addStillEntity(new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i));
                }
            }
        }
    }
}
