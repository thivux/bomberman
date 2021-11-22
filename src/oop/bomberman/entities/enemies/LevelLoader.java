package oop.bomberman.entities.enemies;

import oop.bomberman.control.Handler;
import oop.bomberman.entities.Bomber;
import oop.bomberman.entities.ID;
import oop.bomberman.entities.stilllEntities.Brick;
import oop.bomberman.entities.stilllEntities.Grass;
import oop.bomberman.entities.stilllEntities.Portal;
import oop.bomberman.entities.stilllEntities.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelLoader {
    private int level;
    private int height;
    private int width;
    char[][] map;
    Handler handler;

    public LevelLoader(Handler handler) {
        this.handler = handler;
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
                        handler.addStillEntity(new Wall(30 * j, 30 * i, ID.Bomber));
                        break;
                    case '*':
                        handler.addStillEntity(new Brick(30 * j, 30 * i, ID.Bomber));
                        break;
                    case 'x':
                        handler.addStillEntity(new Portal(30 * j, 30 * i, ID.Portal));
                        break;
                    case 'p':
                        handler.addStillEntity(new Grass(30 * j, 30 * i, ID.Bomber));
                        handler.addMovingEntity(new Bomber(30 * j, 30 * i, ID.Bomber, handler));
                        break;
                    case '1':
                        handler.addStillEntity(new Grass(30 * j, 30 * i, ID.Bomber));
                        handler.addMovingEntity(new Ballom(30 * j, 30 * i, ID.Bomber, handler));
                        break;
                    case '2':
                        handler.addStillEntity(new Grass(30 * j, 30 * i, ID.Bomber));
                        handler.addMovingEntity(new Oneal(30 * j, 30 * i, ID.Bomber, handler));
                        break;
                    case 'b': // bomb item
                        break;
                    case 'f': // fire item
                        break;
                    case 's': // speed item
                        break;
                    default:
                        handler.addStillEntity(new Grass(30 * j, 30 * i, ID.Bomber));
                }
            }
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public static void main(String[] args) {

    }
}
