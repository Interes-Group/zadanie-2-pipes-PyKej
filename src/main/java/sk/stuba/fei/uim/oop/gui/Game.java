package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public static final int BACKGROUND_COLOUR = 0xff9504;
    public Game() {


        JFrame frame = new JFrame("Water Pipes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,910);
        frame.getContentPane().setBackground(new Color(BACKGROUND_COLOUR));
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLayout(new BorderLayout(0,10)); // defaultne je zapnuty border layout + margin vertikalny 10px


//        Board board = new Board(logic.getCurrentBoardSize());
        GameLogic gameLogic = new GameLogic(frame);
        SideMenu sideMenu = new SideMenu(gameLogic);


        frame.addKeyListener(gameLogic);
//
//        JPanel sideMenu = new JPanel();
//        sideMenu.setBackground(Color.LIGHT_GRAY);

//        JButton buttonRestart = new JButton("RESTART");
//        buttonRestart.addActionListener(logic);
//        buttonRestart.setFocusable(false);
//
//        JSlider slider = new JSlider(JSlider.HORIZONTAL, 6, 12, 6);
//        slider.setMinorTickSpacing(2);
//        slider.setMajorTickSpacing(2);
//        slider.setSnapToTicks(true);
//        slider.setPaintTicks(true);
//        slider.setPaintLabels(true);
//        slider.addChangeListener(logic);
//
//        sideMenu.setLayout(new GridLayout(2, 2));
//        sideMenu.add(logic.getLabel());
//        sideMenu.add(buttonRestart);
//        sideMenu.add(logic.getBoardSizeLabel());
//        sideMenu.add(slider);
//        frame.add(sideMenu, BorderLayout.PAGE_START);

        frame.add(gameLogic.getCurrentBoard(), BorderLayout.CENTER);
        frame.add(sideMenu, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
