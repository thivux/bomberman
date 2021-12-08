package gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final GamePanel gamePanel;
    private final InfoPanel infoPanel;

    public Frame() {
        gamePanel = new GamePanel();
        infoPanel = new InfoPanel();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(infoPanel, BorderLayout.PAGE_START);
        add(gamePanel, BorderLayout.PAGE_END);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        gamePanel.start();
    }
}
