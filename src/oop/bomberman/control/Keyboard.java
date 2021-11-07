package oop.bomberman.control;

import oop.bomberman.entities.Entity;
import oop.bomberman.entities.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
    Handler handler;
    public Keyboard(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (Entity entity : handler.movingEntities) {
            if (entity.getId() == ID.Bomber) {
                if (key == KeyEvent.VK_W) {
                    handler.setUp(true);
                }

                if (key == KeyEvent.VK_A) {
                    handler.setLeft(true);
                }

                if (key == KeyEvent.VK_S) {
                    handler.setDown(true);
                }

                if (key == KeyEvent.VK_D) {
                    handler.setRight(true);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (Entity entity : handler.movingEntities) {
            if (entity.getId() == ID.Bomber) {
                if (key == KeyEvent.VK_W) {
                    handler.setUp(false);
                }

                if (key == KeyEvent.VK_A) {
                    handler.setLeft(false);
                }

                if (key == KeyEvent.VK_S) {
                    handler.setDown(false);
                }

                if (key == KeyEvent.VK_D) {
                    handler.setRight(false);
                }
            }
        }

    }
}
