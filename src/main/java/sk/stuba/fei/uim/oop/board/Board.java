package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.board.tile.Direction;
import sk.stuba.fei.uim.oop.board.tile.Pipe;
import sk.stuba.fei.uim.oop.board.tile.Tile;

import javax.swing.*;
import java.awt.*;

import static sk.stuba.fei.uim.oop.gui.Game.BACKGROUND_COLOUR;

public class Board extends JPanel {
    private Tile[][] tileArray;
    public Board(int dimension) {
       this.initializeBoard(dimension);

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(BACKGROUND_COLOUR));


//        this.checkPlayable(State.BLACK);
    }

    private void initializeBoard(int dimension) {
        this.tileArray = new Tile[dimension][dimension];
        this.setLayout(new GridLayout(dimension, dimension));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tileArray[i][j] = new Tile(Pipe.STRAIGHT, Direction.VERTICAL);
                this.add(this.tileArray[i][j]);
            }
        }
//        this.tileArray[dimension / 2][dimension / 2].setState(State.BLACK);
//        this.tileArray[dimension / 2][dimension / 2 - 1].setState(State.WHITE);
//        this.tileArray[dimension / 2 - 1][dimension / 2].setState(State.WHITE);
//        this.tileArray[dimension / 2 - 1][dimension / 2 - 1].setState(State.BLACK);
    }
}
