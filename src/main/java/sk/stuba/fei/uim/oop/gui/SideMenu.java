package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel {

    public SideMenu(GameLogic gameLogic) {
//        this.setBorder(BorderFactory.createEmptyBorder(80, 50, 50, 50));
        this.setPreferredSize(new Dimension(1, 80));
        this.setBackground(Color.GREEN);









//        JPanel sideMenu = new JPanel();

        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(gameLogic);
        buttonRestart.setFocusable(false);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 6, 12, 6);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(gameLogic);

        //todo niektoré budú iné komponenty
        this.setLayout(new GridLayout(2, 2));
        this.add(gameLogic.getLabel());
        this.add(buttonRestart);
        this.add(gameLogic.getBoardSizeLabel());
        this.add(slider);

    }
}
