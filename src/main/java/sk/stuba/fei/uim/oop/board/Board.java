package sk.stuba.fei.uim.oop.board;

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
    private Tile[][] tileArray;
    private int dimension;

    private Tile start;
    private Tile finish;


    public Board(int dimension) {
        this.rand = new Random();

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
                this.tileArray[i][j] = new Tile(STRAIGHT_CORNER, Direction.NONE, Direction.NONE, i,  j); //todo táto část kódu bude asi potrebovať zmenu
                this.add(this.tileArray[i][j]);
            }
        }


        chooseStartFinish();


        ArrayList<Tile> route = generaterRoute();


//        for (int i = 0; i< route.size(); i++){
//            route.get(i).setPipe(STRAIGHT);
//            route.get(i).setDirection1(Direction.U);
//            route.get(i).setDirection2(Direction.D);
//        }

        setTipeOfPipes(route);
    }





    public ArrayList<Tile> generaterRoute() {
        ArrayList<Tile> route = new ArrayList<Tile>();
        Tile current = start;

        start.setVisited(true);
        route.add(start);


        System.out.println(finish.getPipe() + " "+ finish.getPosX() + " "+ finish.getPosY());
        while (current.getPipe() != END) {

            System.out.println(current.getPipe() + " "+ current.getPosX() + " "+ current.getPosY());
            ArrayList<Tile> neighbours = new ArrayList<Tile>();
            checkNeighbours(current, neighbours);

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

        return route;
    }


    private void setTipeOfPipes( ArrayList<Tile> route){
        Tile current;
        Tile next;

        for (int i = 0; i<route.size()-1; i++){
            current = route.get(i);
            next =  route.get(i+1);


            //hore
            if (current.getPosX() >next.getPosX()){
                current.setDirection2(Direction.U);
                next.setDirection1(Direction.D);
            }

            //dole
            if (current.getPosX() < next.getPosX()){
                current.setDirection2(Direction.D);
                next.setDirection1(Direction.U);
            }

            //v pravo
            if (current.getPosY() >next.getPosY()){
                current.setDirection2(Direction.L);
                next.setDirection1(Direction.R);
            }

            //v lavo
            if (current.getPosY() <next.getPosY()){
                current.setDirection2(Direction.R);
                next.setDirection1(Direction.L);
            }
        }
    }

    private  void checkNeighbours(Tile currTile, ArrayList<Tile> neighbours){
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



    private int getRand(int max) {
        int randomNum = rand.nextInt(max);
        return randomNum;
    }

    private void chooseStartFinish(){
        start = tileArray[getRand(dimension)][0];
        start.setPipe(START);
        start.setDirection1(Direction.R);
        start.setDirection2(Direction.NONE);

        finish = tileArray[getRand(dimension)][dimension-1];
        finish.setPipe(END);
        finish.setDirection1(Direction.U);
        finish.setDirection2(Direction.NONE);
    }
}
