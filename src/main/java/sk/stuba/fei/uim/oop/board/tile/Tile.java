package sk.stuba.fei.uim.oop.board.tile;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Tile extends JPanel {

    @Setter
    private Direction direction;
    @Setter
    private Pipe pipe;

    @Setter @Getter
    private Direction direction1;
    @Setter @Getter
    private Direction direction2;

    private int pipeWidth;
    private int pipeHeight;
    private int connectorWidth;
    private int connectorHeight;

    private int middlePosition;

    @Setter
    private boolean highlight;

    public Tile(Pipe pipe, Direction direction1, Direction direction2 ) {
        this.direction1 = direction1;
        this.direction2 = direction2;

        this.pipe = pipe;
        this.direction = direction;

//        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLUE);
        System.out.println("fockjeeee");

    }





    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        if (this.highlight) {
            this.setBorder(BorderFactory.createLineBorder(Color.green));
//            g.setColor(Color.GREEN);
//            ((Graphics2D) g).setStroke(new BasicStroke(3));
            this.highlight = false;
        } else {
//            g.setColor(Color.GRAY);
            this.setBorder(BorderFactory.createLineBorder(Color.black));
//            ((Graphics2D) g).setStroke(new BasicStroke(3));
        }




//            g.setColor(Color.DARK_GRAY);
//            g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), 0,
//                    (int) (this.getWidth() * 0.25), (int) (this.getHeight()));
//
//            g.setColor(Color.PINK);
//            g.fillRect(middlePosition((int) (this.getWidth() * 0.35)), 0,
//            (int) (this.getWidth() * 0.35), (int) (this.getHeight() * 0.08));
//
//            g.fillRect(middlePosition((int) (this.getWidth() * 0.35)), (int) (0 + this.getHeight() * 0.92),
//            (int) (this.getWidth() * 0.35), (int) (this.getHeight() * 0.08));

//        System.out.println("VElkosti stvorca: "+this.getHeight() + " " + this.getWidth());


            // todo neviem či premenné môžu ostať tuna
            this.pipeWidth = (int) (this.getWidth() * 0.25);
            this.pipeHeight = (int) ((this.getWidth() * 0.5)+ pipeWidth *0.5);
            this.connectorWidth = (int) (this.getWidth() * 0.35);
            this.connectorHeight = (int) (this.getHeight() * 0.08);
            this.middlePosition =(int) ((this.getWidth() * 0.5)-(this.getWidth() * 0.25)*0.5);



//            g.setColor(Color.DARK_GRAY);
//            g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), 0,
//                    pipeWidth, (int) (this.getHeight() * 0.5) );
//
////
////
//            g.setColor(Color.PINK);
//            g.fillRect(middlePosition(connectorWidth), 0,
//                    connectorWidth, connectorHeight);
//
//              g.setColor(Color.GRAY);
////            g.fillRect(0, middlePosition((int) (this.getHeight() * 0.25)),
////                    pipeHeight,  pipeWidth );
//
//            g.fillRect(0, middlePosition(connectorWidth),
//                    connectorHeight, connectorWidth);



//        printPipeL(g);
//        printPipeU(g);
//        printPipeD(g);
//        printPipeR(g);


        if(direction1 == Direction.L || direction2 == Direction.L){
            g.setColor(Color.GRAY);
            g.fillRect(0, middlePosition((int) (this.getHeight() * 0.25)),
                    pipeHeight,  pipeWidth );

            g.setColor(Color.PINK);
            g.fillRect(0, middlePosition(connectorWidth),
                    connectorHeight, connectorWidth);
        }

        if(direction1 == Direction.U || direction2 == Direction.U){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), 0,
                    pipeWidth, pipeHeight);

            g.setColor(Color.PINK);
            g.fillRect(middlePosition(connectorWidth), 0,
                    connectorWidth, connectorHeight);
        }

        if(direction1 == Direction.R || direction2 == Direction.R){
            g.setColor(Color.YELLOW);
            g.fillRect((this.getWidth()-pipeHeight), middlePosition((int) (this.getHeight() * 0.25)),
                    pipeHeight,  pipeWidth );

            g.setColor(Color.PINK);
            g.fillRect((this.getWidth()-connectorHeight), middlePosition(connectorWidth),
                    connectorHeight, connectorWidth);
        }
        if(direction1 == Direction.D || direction2 == Direction.D){
            g.setColor(Color.RED);
            g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), middlePosition(pipeWidth),
                    pipeWidth, pipeHeight);

            g.setColor(Color.ORANGE);
            g.fillRect(middlePosition(connectorWidth), (this.getHeight()-connectorHeight),
                    connectorWidth, connectorHeight);

//            Direction.values()[2];


//            Random random = new Random();
//            int index = random.nextInt(Direction.values().length);
//            Direction.values()[index];


//            Direction[] directions = Direction.values();
//            int index = 2;
//            Direction direction = directions[index];
//
////            int index = direction1.ordinal();
//            System.out.println("Ordinallll: " + index);
//            System.out.println("Directionl: " + direction);
        }

//        switch (direction) {
//            case U_R:
////                System.out.println("Up right");
//                printPipeU(g);
//                printPipeR(g);
//                break;
//            case D_R:
////                System.out.println("Down right");
//                printPipeD(g);
//                printPipeR(g);
//                break;
//            case D_L:
////                System.out.println("Down left");
//                printPipeD(g);
//                printPipeL(g);
//                break;
//            case U_L:
////                System.out.println("Up left");
//                printPipeL(g);
//                printPipeU(g);
//                break;
//            case HORIZONTAL:
////                System.out.println("Horizontal");
//                printPipeR(g);
//                printPipeL(g);
//                break;
//            case VERTICAL:
////                System.out.println("Vertical");
//                printPipeU(g);
//                printPipeD(g);
//                break;
//            case L:
////                System.out.println("Left");
//                printPipeL(g);
//                break;
//            case R:
////                System.out.println("Right");
//                printPipeR(g);
//                break;
//            case U:
////                System.out.println("Up");
//                printPipeU(g);
//                break;
//            case D:
////                System.out.println("Down");
//                printPipeD(g);
//                break;
//        }




//            g.setColor(Color.BLACK);
//            ((Graphics2D) g).setStroke(new BasicStroke(1));


    }

    private int middlePosition(int width){
        int middle = (int) ((this.getWidth() * 0.5)- width *0.5);
        return middle;
    }

//    private void printPipeL(Graphics g){
//        g.setColor(Color.GRAY);
//        g.fillRect(0, middlePosition((int) (this.getHeight() * 0.25)),
//                pipeHeight,  pipeWidth );
//
//        g.setColor(Color.PINK);
//        g.fillRect(0, middlePosition(connectorWidth),
//                connectorHeight, connectorWidth);
//    }
//
//    private void printPipeR(Graphics g){
//        g.setColor(Color.YELLOW);
//        g.fillRect((this.getWidth()-pipeHeight), middlePosition((int) (this.getHeight() * 0.25)),
//                pipeHeight,  pipeWidth );
//
//        g.setColor(Color.PINK);
//        g.fillRect((this.getWidth()-connectorHeight), middlePosition(connectorWidth),
//                connectorHeight, connectorWidth);
//    }
//
//    private void printPipeU(Graphics g){
//        g.setColor(Color.DARK_GRAY);
//        g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), 0,
//                pipeWidth, pipeHeight);
//
//        g.setColor(Color.PINK);
//        g.fillRect(middlePosition(connectorWidth), 0,
//                connectorWidth, connectorHeight);
//    }
//
//    private void printPipeD(Graphics g){
//        g.setColor(Color.RED);
//        g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), middlePosition(pipeWidth),
//                pipeWidth, pipeHeight);
//
//        g.setColor(Color.ORANGE);
//        g.fillRect(middlePosition(connectorWidth), (this.getHeight()-connectorHeight),
//                connectorWidth, connectorHeight);
//    }



    public void rotate(){
        Direction[] directions = Direction.values();

        int dir1Index = 1+ direction1.ordinal();
        int dir2Index = 1+ direction2.ordinal();

        if (dir1Index >= directions.length){
            dir1Index =0;
        }
        if (dir2Index >= directions.length){
            dir2Index =0;
        }

        direction1 = directions[dir1Index];
        direction2 = directions[dir2Index];
    }



}
