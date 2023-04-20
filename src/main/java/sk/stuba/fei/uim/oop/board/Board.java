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
                this.tileArray[i][j] = new Tile(Pipe.CORNER, Direction.D, Direction.R, i,  j); //todo táto část kódu bude asi potrebovať zmenu
                this.add(this.tileArray[i][j]);
            }
        }


        chooseStartFinish();


        ArrayList<Tile> route = generaterRoute();
        System.out.println("Tuna je cesaaaaaa" + route);

        for (int i = 0; i< route.size(); i++){
            route.get(i).setPipe(STRAIGHT);
            route.get(i).setDirection1(Direction.U);
            route.get(i).setDirection2(Direction.D);
        }
    }





    public ArrayList<Tile> generaterRoute() {
//        Set<Tile> visited = new HashSet<>();
        ArrayList<Tile> route = new ArrayList<Tile>();
        Tile current = start;

        start.setVisited(true);
        route.add(start);

//        current != finish
        int i=1;
        System.out.println(finish.getPipe() + " "+ finish.getPosX() + " "+ finish.getPosY());
        while (current.getPipe() != END) {
            System.out.println("ICKO:" + i);
            System.out.println(current.getPipe() + " "+ current.getPosX() + " "+ current.getPosY());
            ArrayList<Tile> neighbours = new ArrayList<Tile>();
            checkNeighbours(current, neighbours);

//            for (i = 0; i<neighbours.size(); i++){
//                if (neighbours.get(i)==finish){
//                    return route;
//                }
//            }



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
            i++;
        }

        return route;
    }

    private  void checkNeighbours(Tile currTile, ArrayList<Tile> neighbours){
//        Tile neighbourU = tileArray[currTile.getPosX()][currTile.getPosY()+1];
//        Tile neighbourR = tileArray[currTile.getPosX()+1][currTile.getPosY()];
//        Tile neighbourD = tileArray[currTile.getPosX()][currTile.getPosY()-1];
//        Tile neighbourL = tileArray[currTile.getPosX()-1][currTile.getPosY()];

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
