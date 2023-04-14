package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.board.tile.Tile;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Tile[][] tileArray;
    public Board(int dimension) {
       this.initializeBoard(dimension);

        //this.setBorder(BorderFactory.createEmptyBorder(180, 50, 50, 50));
        this.setBackground(Color.YELLOW);


//        this.checkPlayable(State.BLACK);
    }

    private void initializeBoard(int dimension) {
        this.tileArray = new Tile[dimension][dimension];
        this.setLayout(new GridLayout(dimension, dimension));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tileArray[i][j] = new Tile();
                this.add(this.tileArray[i][j]);
            }
        }
//        this.tileArray[dimension / 2][dimension / 2].setState(State.BLACK);
//        this.tileArray[dimension / 2][dimension / 2 - 1].setState(State.WHITE);
//        this.tileArray[dimension / 2 - 1][dimension / 2].setState(State.WHITE);
//        this.tileArray[dimension / 2 - 1][dimension / 2 - 1].setState(State.BLACK);
    }
}
