package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.tile.Direction;
import sk.stuba.fei.uim.oop.board.tile.Pipe;
import sk.stuba.fei.uim.oop.board.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static sk.stuba.fei.uim.oop.board.tile.Pipe.*;
import static sk.stuba.fei.uim.oop.gui.Game.BACKGROUND_COLOUR;

public class Board extends JPanel {

    private final Random rand;

    @Getter
    private Tile[][] tileArray;

    @Getter
    private int dimension;

    private ArrayList<Tile> route;
    private ArrayList<Tile> neighbours;

    @Getter
    private Tile start;

    @Getter
    private Tile finish;


    public Board(int dimension) {
        this.rand = new Random();
        this.route = new ArrayList<Tile>();
        this.neighbours = new ArrayList<Tile>();

        this.dimension = dimension;

        this.initializeBoard();

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(BACKGROUND_COLOUR));
        System.out.println("Board sa opakuje");

//        this.checkPlayable(State.BLACK);
    }

    private void initializeBoard() {
        this.tileArray = new Tile[dimension][dimension];
        this.setLayout(new GridLayout(dimension, dimension));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tileArray[i][j] = new Tile(i, j); //todo táto část kódu bude asi potrebovať zmenu
                this.add(this.tileArray[i][j]);
            }
        }

        chooseStartFinish();
        generaterRoute();
        setTipeOfPipes();
        randomRotate();
    }





    public void generaterRoute() {
        Tile current = start;
        start.setVisited(true);
        route.add(start);

        while (current.getPipe() != END) {
            System.out.println(current.getPipe() + " "+ current.getPosX() + " "+ current.getPosY());
            neighbours.clear();
            checkNeighbours(current);

            if (neighbours.size() == 0) {
                route.remove(current);
                current = route.get(route.size() - 1);
            }
            else if (neighbours.size() == 1) {
                Tile next = neighbours.get(0);
                next.setVisited(true);
                route.add(next);
                current = next;
            }
            else {
                Tile next = neighbours.get(getRand(neighbours.size()));
                next.setVisited(true);
                route.add(next);
                current = next;
            }
        }
    }

    private void randomRotate(){
        for(int i = 0; i<route.size(); i++){
            for (int j = 0; j<=getRand(6); j++ ){
                route.get(i).rotate();
            }
        }
        this.repaint();
    }


    public void checkPipes(Tile currPipe){
            neighbours.clear();
            checkNeighbourPipe(currPipe);
        boolean connectedPipe;

            for (int j = 0; j< neighbours.size(); j++){
                Tile currNeighbour = neighbours.get(j);
                connectedPipe = false;


                if ((currPipe.isWater()) && !(currNeighbour.isWater())){

                    if (    (currPipe.getPosX() > currNeighbour.getPosX()) && (currPipe.getPosY() == currNeighbour.getPosY()) &&
                            (currPipe.getDirection1()==Direction.U || currPipe.getDirection2()==Direction.U) &&
                            (currNeighbour.getDirection1() == Direction.D ||currNeighbour.getDirection2() == Direction.D) ){
                        connectedPipe = true;
                    }

                    else if (    (currPipe.getPosX() < currNeighbour.getPosX()) && (currPipe.getPosY() == currNeighbour.getPosY()) &&
                            (currPipe.getDirection1()==Direction.D || currPipe.getDirection2()==Direction.D) &&
                            (currNeighbour.getDirection1() == Direction.U ||currNeighbour.getDirection2() == Direction.U) ){
                        connectedPipe = true;
                    }

                    else if (    (currPipe.getPosY() > currNeighbour.getPosY()) && (currPipe.getPosX() == currNeighbour.getPosX()) &&
                            (currPipe.getDirection1()==Direction.L || currPipe.getDirection2()==Direction.L) &&
                            (currNeighbour.getDirection1() == Direction.R ||currNeighbour.getDirection2() == Direction.R) ){
                        connectedPipe = true;
                    }

                    else if (    (currPipe.getPosY() < currNeighbour.getPosY()) && (currPipe.getPosX() == currNeighbour.getPosX()) &&
                            (currPipe.getDirection1()==Direction.R || currPipe.getDirection2()==Direction.R) &&
                            (currNeighbour.getDirection1() == Direction.L ||currNeighbour.getDirection2() == Direction.L) ){
                        connectedPipe = true;
                    }

                    if (connectedPipe == true){
                        System.out.println("Voda " + currNeighbour.getPipe() + " "+ currNeighbour.getPosX() + " "+ currNeighbour.getPosY());
                        currNeighbour.setWater(true);
                        checkPipes(currNeighbour);
                        break;
                    }
                }
            }
    }

    public void setWater(){
        for (int i = 0; i<route.size(); i++){
            route.get(i).setWater(false);
        }
    }

    private void setTipeOfPipes(){
        Tile current;
        Tile next;

        for (int i = 0; i<route.size()-1; i++){
            current = route.get(i);
            next =  route.get(i+1);

            if (i !=0){
                current.setPipe(STRAIGHT_CORNER);
            }

            if (current.getPosX() >next.getPosX()){
                current.setDirection2(Direction.U);
                next.setDirection1(Direction.D);
            }

            if (current.getPosX() < next.getPosX()){
                current.setDirection2(Direction.D);
                next.setDirection1(Direction.U);
            }

            if (current.getPosY() >next.getPosY()){
                current.setDirection2(Direction.L);
                next.setDirection1(Direction.R);
            }

            if (current.getPosY() <next.getPosY()){
                current.setDirection2(Direction.R);
                next.setDirection1(Direction.L);
            }
        }
    }

    private  void checkNeighbours(Tile currTile){
        if (currTile.getPosY()+1 < dimension && !(tileArray[currTile.getPosX()][currTile.getPosY()+1].isVisited())){
            neighbours.add(tileArray[currTile.getPosX()][currTile.getPosY()+1]);
        }
        if (currTile.getPosX()+1 < dimension && !(tileArray[currTile.getPosX()+1][currTile.getPosY()].isVisited())){
            neighbours.add(tileArray[currTile.getPosX()+1][currTile.getPosY()]);
        }
        if (currTile.getPosY()-1 >= 0 && !(tileArray[currTile.getPosX()][currTile.getPosY()-1].isVisited())){
            neighbours.add(tileArray[currTile.getPosX()][currTile.getPosY()-1]);
        }
        if (currTile.getPosX()-1 >= 0 && !(tileArray[currTile.getPosX()-1][currTile.getPosY()].isVisited())){
            neighbours.add(tileArray[currTile.getPosX()-1][currTile.getPosY()]);
        }
    }

    private  void checkNeighbourPipe(Tile currTile){
        if (    currTile.getPosY()+1 < dimension &&
                ((tileArray[currTile.getPosX()][currTile.getPosY()+1].getPipe()==END) ||
                (tileArray[currTile.getPosX()][currTile.getPosY()+1].getPipe()==STRAIGHT_CORNER))){
            neighbours.add(tileArray[currTile.getPosX()][currTile.getPosY()+1]);
        }
        if (    currTile.getPosX()+1 < dimension &&
                ((tileArray[currTile.getPosX()+1][currTile.getPosY()].getPipe()==END) ||
                (tileArray[currTile.getPosX()+1][currTile.getPosY()].getPipe()==STRAIGHT_CORNER))){
            neighbours.add(tileArray[currTile.getPosX()+1][currTile.getPosY()]);
        }
        if (    currTile.getPosY()-1 >= 0 &&
                ((tileArray[currTile.getPosX()][currTile.getPosY()-1].getPipe()==END) ||
                (tileArray[currTile.getPosX()][currTile.getPosY()-1].getPipe()==STRAIGHT_CORNER))){
            neighbours.add(tileArray[currTile.getPosX()][currTile.getPosY()-1]);
        }
        if (    currTile.getPosX()-1 >= 0 &&
                ((tileArray[currTile.getPosX()-1][currTile.getPosY()].getPipe()==END) ||
                (tileArray[currTile.getPosX()-1][currTile.getPosY()].getPipe()==STRAIGHT_CORNER))){
            neighbours.add(tileArray[currTile.getPosX()-1][currTile.getPosY()]);
        }
    }

    private int getRand(int max) {
        int randomNum = rand.nextInt(max);
        return randomNum;
    }

    private void chooseStartFinish(){
        start = tileArray[getRand(dimension)][0];
        start.setPipe(START);
        start.setDirection1(Direction.NONE);
        start.setDirection2(Direction.R);

        finish = tileArray[getRand(dimension)][dimension-1];
        finish.setPipe(END);
        finish.setDirection1(Direction.U);
        finish.setDirection2(Direction.NONE);
    }
}
