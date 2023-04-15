package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private JFrame mainGame;

    @Getter
    private Board currentBoard;
    @Getter
    private JLabel label;
    @Getter
    private JLabel boardSizeLabel;
    @Getter
    private int currentBoardSize;

    private HashMap<String, Integer> optionValues;

    public GameLogic(JFrame mainGame) {

        this.mainGame = mainGame;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();

        optionValues = new HashMap<>();
        optionValues.put("Easy (8x8)", 8);
        optionValues.put("Medium (9x9)", 9);
        optionValues.put("Hard (10x10)", 10);

        updateNameLabel();
        updateBoardSizeLabel();

        initializeNewBoard(currentBoardSize);


//        this.mainGame.add(this.currentBoard);
        //todo tuna daj iné komponenty
        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();
        this.updateNameLabel();
        this.updateBoardSizeLabel();





    }

    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);

//        this.currentBoard.addMouseMotionListener(this);
//        this.currentBoard.addMouseListener(this);

    }


    private void updateNameLabel() {
        this.label.setText("PLAYER: " + " is PLAYING");
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    private void updateBoardSizeLabel() {
        this.boardSizeLabel.setText("CURRENT BOARD SIZE: " + this.currentBoardSize);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }


    private void gameRestart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateNameLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String actionCommand = e.getActionCommand();
        if ("buttonRestart".equals(actionCommand)) {
            this.gameRestart();
            this.mainGame.revalidate();
            this.mainGame.repaint();
            this.mainGame.setFocusable(true);
            this.mainGame.requestFocus();
        }
        else if ("buttonCheck".equals(actionCommand)) {
            System.out.println("Nict!!!!!!!!!!");
        }

        else if ("comboBox".equals(actionCommand)) {
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            String selectedOption = (String) combo.getSelectedItem();
            System.out.println(selectedOption);


            currentBoardSize = optionValues.get(selectedOption);

            this.updateBoardSizeLabel();
            this.gameRestart();
            this.mainGame.setFocusable(true);
            this.mainGame.requestFocus();
        }



    }

}
