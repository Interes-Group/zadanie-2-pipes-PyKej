package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;
import javax.swing.*;
import java.awt.*;

public class Game {
    public static final int BACKGROUND_COLOUR = 0xff9504;

    public Game() {
        JFrame frame = new JFrame("Water Pipes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 910);
        frame.getContentPane().setBackground(new Color(BACKGROUND_COLOUR));
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLayout(new BorderLayout(0, 10));
        GameLogic gameLogic = new GameLogic(frame);
        SideMenu sideMenu = new SideMenu(gameLogic);
        frame.addKeyListener(gameLogic);
        frame.add(gameLogic.getCurrentBoard(), BorderLayout.CENTER);
        frame.add(sideMenu, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
