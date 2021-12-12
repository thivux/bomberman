package gui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private static JLabel levelLabel;
    private static JLabel scoreLabel;
    private static JLabel livesLabel;
    private static GamePanel gamePanel;

    public InfoPanel(GamePanel _gamePanel) {
        this.gamePanel = _gamePanel;
        setLayout(new GridLayout());

        levelLabel = new JLabel("Level: " + gamePanel.getLevel());
        levelLabel.setForeground(Color.white);
        levelLabel.setHorizontalAlignment(JLabel.CENTER);

        scoreLabel = new JLabel("Scores: " + gamePanel.getScore());
        scoreLabel.setForeground(Color.white);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        livesLabel = new JLabel("Lives: " + gamePanel.getLives());
        livesLabel.setForeground(Color.white);
        livesLabel.setHorizontalAlignment(JLabel.CENTER);

        add(levelLabel);
        add(scoreLabel);
        add(livesLabel);


        setBackground(Color.black);
        setPreferredSize(new Dimension(0, 40));
    }

    public static void updateLevel() {
        levelLabel.setText("Level: " + gamePanel.getLevel());
    }

    public static void updateLives() {
        livesLabel.setText("Lives: " + gamePanel.getLives());

    }

    public static void updateScores() {
        scoreLabel.setText("Scores: " + gamePanel.getScore());
    }

}
