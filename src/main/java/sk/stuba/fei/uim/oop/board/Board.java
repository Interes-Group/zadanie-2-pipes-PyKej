package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.board.tile.Direction;
import sk.stuba.fei.uim.oop.board.tile.Pipe;
import sk.stuba.fei.uim.oop.board.tile.Tile;
import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static sk.stuba.fei.uim.oop.board.tile.Pipe.END;
import static sk.stuba.fei.uim.oop.board.tile.Pipe.START;
import static sk.stuba.fei.uim.oop.gui.Game.BACKGROUND_COLOUR;

public class Board extends JPanel {

    private final Random rand;
    private Tile[][] tileArray;


    public Board(int dimension) {
        this.rand = new Random();

        this.initializeBoard(dimension);

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(BACKGROUND_COLOUR));
        System.out.println("Board sa opakuje");

//        this.checkPlayable(State.BLACK);
    }

    private void initializeBoard(int dimension) {
        this.tileArray = new Tile[dimension][dimension];
        this.setLayout(new GridLayout(dimension, dimension));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tileArray[j][i] = new Tile(Pipe.CORNER, Direction.D, Direction.R); //todo táto část kódu bude asi potrebovať zmenu
                this.add(this.tileArray[j][i]);
            }
        }


        chooseStartEnd(dimension);


//        this.tileArray[dimension / 2][dimension / 2].setState(State.BLACK);
//        this.tileArray[dimension / 2][dimension / 2 - 1].setState(State.WHITE);
//        this.tileArray[dimension / 2 - 1][dimension / 2].setState(State.WHITE);
//        this.tileArray[dimension / 2 - 1][dimension / 2 - 1].setState(State.BLACK);
    }



    private int getRand(int max) {
        int randomNum = rand.nextInt(max);
        return randomNum;
    }

    private void chooseStartEnd(int dimension){

        int startY = getRand(dimension);
        int endY = getRand(dimension);

        this.tileArray[0][startY].setPipe(START);
        this.tileArray[0][startY].setDirection(Direction.R);

        this.tileArray[dimension-1][endY].setPipe(END);
        this.tileArray[dimension-1][endY].setDirection(Direction.U);
    }
}
