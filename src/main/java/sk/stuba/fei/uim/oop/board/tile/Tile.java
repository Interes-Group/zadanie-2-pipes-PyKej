package sk.stuba.fei.uim.oop.board.tile;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    public Tile() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLUE);
    }





    public void paintComponent(Graphics g) {
        System.out.println("ide??");
        super.paintComponent(g);

                ((Graphics2D) g).setStroke(new BasicStroke(3));
                g.drawOval((int) (0 + this.getWidth() * 0.75), (int) (0 + this.getHeight() * 0.05),
                        (int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.9));
                g.setColor(Color.BLACK);
                ((Graphics2D) g).setStroke(new BasicStroke(1));

    }





}
