package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;

import javax.swing.*;

public class GameLogic {
    public static final int INITIAL_BOARD_SIZE = 6;
    private JFrame mainGame;

    @Getter
    private Board currentBoard;
    @Getter
    private JLabel label;
    @Getter
    private JLabel boardSizeLabel;
    @Getter
    private int currentBoardSize;

    public GameLogic(JFrame mainGame) {

        this.mainGame = mainGame;
        this.currentBoardSize = INITIAL_BOARD_SIZE;


        initializeNewBoard(currentBoardSize);


//        this.mainGame.add(this.currentBoard);

        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();
//        this.updateNameLabel();
//        this.updateBoardSizeLabel();

    }

    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);

//        this.currentBoard.addMouseMotionListener(this);
//        this.currentBoard.addMouseListener(this);
    }
}
