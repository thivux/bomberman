package control;

import gui.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    public Keyboard(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gamePanel.gameState == gamePanel.titleState) {
            if (code == KeyEvent.VK_W) {
                gamePanel.commandNum--;
                if (gamePanel.commandNum < 0) {
                    gamePanel.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gamePanel.commandNum++;
                if (gamePanel.commandNum > 2) {
                    gamePanel.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.commandNum == 0) {
                    gamePanel.gameState = 1;
                } else if (gamePanel.commandNum == 1) {
                    //TODO: load game
                } else if (gamePanel.commandNum == 2) {
                    System.exit(0);
                }
            }
        } else if (gamePanel.gameState == gamePanel.endState) {
            if (code == KeyEvent.VK_W) {
                gamePanel.commandNum--;
                if (gamePanel.commandNum < 0) {
                    gamePanel.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gamePanel.commandNum++;
                if (gamePanel.commandNum > 1) {
                    gamePanel.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.commandNum == 0) {
                    gamePanel.gameState = 1;
                    gamePanel.resetGame();
                } else if (gamePanel.commandNum == 1) {
                    System.exit(0);
                }
            }
        } else {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }

            if (code == KeyEvent.VK_P) {
                if (gamePanel.gameState == gamePanel.playState) {
                    gamePanel.gameState = gamePanel.pauseState;
                } else if (gamePanel.gameState == gamePanel.pauseState) {
                    gamePanel.gameState = gamePanel.playState;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
