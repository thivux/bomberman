package level;

import entities.LayeredEntity;
import entities.animatedEntities.characters.Ballom;
import entities.animatedEntities.characters.Bomber;
import entities.animatedEntities.characters.Oneal;
import entities.tiles.Brick;
import entities.tiles.Grass;
import entities.tiles.Portal;
import entities.tiles.Wall;
import entities.tiles.powerup.PowerupBombs;
import entities.tiles.powerup.PowerupFlames;
import entities.tiles.powerup.PowerupSpeed;
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
            BufferedReader br = new BufferedReader(new FileReader("res\\levels\\Level" + this.board.getLevel() + ".txt"));
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
            br.close();
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
                        Grass grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        Brick brick = new Brick(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        LayeredEntity layeredEntity = new LayeredEntity(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, grass, brick);
                        board.addStillEntity(layeredEntity);
                        board.setTile(j, i, layeredEntity);
                        break;
                    case 'x':
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        Portal portal = new Portal(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, this.board);
                        brick = new Brick(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        layeredEntity = new LayeredEntity(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, grass, portal, brick);
                        board.addStillEntity(layeredEntity);
                        board.setTile(j, i, layeredEntity);
                        break;
                    case 'p':
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
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
                    case 'b':
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        PowerupBombs powerupBombs = new PowerupBombs(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, this.board);
                        brick = new Brick(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        layeredEntity = new LayeredEntity(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, grass, powerupBombs, brick);
                        powerupBombs.setLayeredEntity(layeredEntity);
                        board.addStillEntity(layeredEntity);
                        board.setTile(j, i, layeredEntity);
                        break;
                    case 'f':
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        PowerupFlames powerupFlames = new PowerupFlames(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, this.board);
                        brick = new Brick(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        layeredEntity = new LayeredEntity(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, grass, powerupFlames, brick);
                        powerupFlames.setLayeredEntity(layeredEntity);
                        board.addStillEntity(layeredEntity);
                        board.setTile(j, i, layeredEntity);
                        break;
                    case 's':
                        grass = new Grass(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        PowerupSpeed powerupSpeed = new PowerupSpeed(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, this.board);
                        brick = new Brick(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i);
                        layeredEntity = new LayeredEntity(GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE * i, grass, powerupSpeed, brick);
                        powerupSpeed.setLayeredEntity(layeredEntity);
                        board.addStillEntity(layeredEntity);
                        board.setTile(j, i, layeredEntity);
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
