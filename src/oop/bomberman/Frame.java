package oop.bomberman;

import javax.swing.*;
import java.awt.*;

public class Frame {
    public Frame(int width, int height, String title, BombermanGame game) {
        JFrame frame = new JFrame((title));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
