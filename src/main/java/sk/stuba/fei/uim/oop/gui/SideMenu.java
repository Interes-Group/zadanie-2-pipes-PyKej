package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

import static sk.stuba.fei.uim.oop.gui.Game.BACKGROUND_COLOUR;

public class SideMenu extends JPanel {

    public SideMenu(GameLogic gameLogic) {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(1, 80));
        this.setBackground(new Color(BACKGROUND_COLOUR));









//        JPanel sideMenu = new JPanel();

        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.setActionCommand("buttonRestart");
        buttonRestart.addActionListener(gameLogic);
        buttonRestart.setFocusable(false);



        JButton buttonCheck = new JButton("CHECK");
        buttonCheck.setActionCommand("buttonCheck");
        buttonCheck.addActionListener(gameLogic);
        buttonCheck.setFocusable(false);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(200, 1));
        buttonsPanel.setLayout(new GridLayout(2, 1));
        buttonsPanel.add(buttonCheck);
        buttonsPanel.add(buttonRestart);

        buttonCheck.setBackground(new Color(0x6280FC));
        buttonCheck.setForeground(Color.WHITE);

        buttonRestart.setBackground(new Color(0xA0090B));
        buttonRestart.setForeground(Color.WHITE);

        String[] items = {"Easy (8x8)", "Medium (9x9)", "Hard (10x10)"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setActionCommand("comboBox");
        comboBox.addActionListener(gameLogic);
        comboBox.setFocusable(false);
//        comboBox.setPreferredSize(new Dimension(100, 30));


        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(new Color(BACKGROUND_COLOUR));
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        comboBoxPanel.setPreferredSize(new Dimension(100, 1));
        comboBoxPanel.setLayout(new GridLayout(2, 1));
        comboBoxPanel.add(gameLogic.getBoardSizeLabel());
        comboBoxPanel.add(comboBox);


        gameLogic.getLabel().setPreferredSize(new Dimension(200, 1));



        this.setLayout(new BorderLayout());

        this.add(gameLogic.getLabel(), BorderLayout.WEST);

//        this.add(gameLogic.getBoardSizeLabel(), BorderLayout.CENTER);
        this.add(comboBoxPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);
//        this.add(slider);

    }
}
