package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.board.tile.Tile;

import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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
    @Getter
    private int currentLevel;




    private HashMap<String, Integer> optionValues;

    public GameLogic(JFrame mainGame) {



        this.mainGame = mainGame;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();

        currentLevel = 1;

        optionValues = new HashMap<>();
        optionValues.put("Easy (8x8)", 8);
        optionValues.put("Medium (9x9)", 9);
        optionValues.put("Hard (10x10)", 10);

        updateLevelLabel();
        updateBoardSizeLabel();

        initializeNewBoard(currentBoardSize);


        //todo tuna daj iné komponenty
        this.label = new JLabel();
        this.boardSizeLabel = new JLabel();
        this.updateLevelLabel();
        this.updateBoardSizeLabel();





    }

    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);

        // todo mouse motion listenery neviem čo je zle
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);

    }


    private void updateLevelLabel() {
        this.label.setText("Current level is: " + currentLevel);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    private void updateBoardSizeLabel() {
        this.boardSizeLabel.setText("Current board size: " + this.currentBoardSize);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }


    private void gameRestart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateLevelLabel();
    }


    private void check() {

        this.currentBoard.setWater();
        this.currentBoard.getStart().setWater(true);
        this.currentBoard.checkPipes(this.currentBoard.getStart());

        if (this.currentBoard.getFinish().isWater()){
            currentLevel++;
            this.gameRestart();
        }

        this.mainGame.revalidate();
        this.mainGame.repaint();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            return;
        }
        else{
            ((Tile) current).setHighlight(true);
        }

//        System.out.println("FUNGUSSSSSS");
//        ((Tile) current).setHighlight(true);

        this.currentBoard.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        this.currentBoard.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("buttonRestart".equals(actionCommand)) {
            currentLevel = 1;
            this.gameRestart();
        }
        else if ("buttonCheck".equals(actionCommand)) {
            check();
        }

        else if ("comboBox".equals(actionCommand)) {
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            String selectedOption = (String) combo.getSelectedItem();
            System.out.println(selectedOption);


            currentBoardSize = optionValues.get(selectedOption);


            currentLevel = 1;
            this.updateBoardSizeLabel();
            this.gameRestart();
            this.mainGame.setFocusable(true);
            this.mainGame.requestFocus();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            return;
        }

        System.out.println("IDE??");
        ((Tile) current).rotate();

        ((Tile) current).getDirection1();
        this.currentBoard.setWater();

        System.out.println("toto som dostal: " +  ((Tile) current).getDirection1());
        this.currentBoard.repaint();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                currentLevel = 1;
                this.gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();
                break;
            case KeyEvent.VK_ENTER:
                check();
                break;
        }
    }

}
