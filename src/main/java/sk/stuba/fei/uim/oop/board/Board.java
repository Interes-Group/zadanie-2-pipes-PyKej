package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.board.tile.Tile;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Tile[][] board;
    public Board(int dimension) {
//        this.initializeBoard(dimension);

        //todo neviem prečo nejde!
        //this.setBorder(BorderFactory.createEmptyBorder(180, 50, 50, 50));
        this.setBackground(Color.YELLOW);


//        this.checkPlayable(State.BLACK);
    }

//    private void initializeBoard(int dimension) {
//        this.board = new Tile[dimension][dimension];
//        this.setLayout(new GridLayout(dimension, dimension));
//        for (int i = 0; i < dimension; i++) {
//            for (int j = 0; j < dimension; j++) {
//                this.board[i][j] = new Tile();
//                this.add(this.board[i][j]);
//            }
//        }
//        this.board[dimension / 2][dimension / 2].setState(State.BLACK);
//        this.board[dimension / 2][dimension / 2 - 1].setState(State.WHITE);
//        this.board[dimension / 2 - 1][dimension / 2].setState(State.WHITE);
//        this.board[dimension / 2 - 1][dimension / 2 - 1].setState(State.BLACK);
//    }
}
