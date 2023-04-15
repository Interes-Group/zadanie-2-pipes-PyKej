package sk.stuba.fei.uim.oop.board.tile;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Tile extends JPanel {

    private Direction direction;
    private Pipe pipe;

    private int pipeWidth;
    private int pipeHeight;
    private int connectorWidth;
    private int connectorHeight;
    private int middlePosition;

    @Setter
    private boolean highlight;

    public Tile(Pipe pipe, Direction direction) {
        this.pipe = pipe;
        this.direction = direction;

        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLUE);


    }





    public void paintComponent(Graphics g) {
        super.paintComponent(g);

            ((Graphics2D) g).setStroke(new BasicStroke(3));

        if (this.highlight) {
            g.setColor(Color.GREEN);
            this.highlight = false;
        } else {
            g.setColor(Color.GRAY);
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

        System.out.println("VElkosti stvorca: "+this.getHeight() + " " + this.getWidth());


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


        switch (direction) {
            case U_R:
                System.out.println("Up right");
                printPipeU(g);
                printPipeR(g);
                break;
            case D_R:
                System.out.println("Down right");
                printPipeD(g);
                printPipeR(g);
                break;
            case D_L:
                System.out.println("Down left");
                printPipeD(g);
                printPipeL(g);
                break;
            case U_L:
                System.out.println("Up left");
                printPipeL(g);
                printPipeU(g);
                break;
            case HORIZONTAL:
                System.out.println("Horizontal");
                printPipeR(g);
                printPipeL(g);
                break;
            case VERTICAL:
                System.out.println("Vertical");
                printPipeU(g);
                printPipeD(g);
                break;
            case L:
                System.out.println("Left");
                printPipeL(g);
                break;
            case R:
                System.out.println("Right");
                printPipeR(g);
                break;
            case U:
                System.out.println("Up");
                printPipeU(g);
                break;
            case D:
                System.out.println("Down");
                printPipeD(g);
                break;
        }




            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(1));

            boolean[] arr = {true, false, true, false};
            rotate(arr);

    }

    private int middlePosition(int width){
        int middle = (int) ((this.getWidth() * 0.5)- width *0.5);
        return middle;
    }

    private void printPipeL(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0, middlePosition((int) (this.getHeight() * 0.25)),
                pipeHeight,  pipeWidth );

        g.setColor(Color.PINK);
        g.fillRect(0, middlePosition(connectorWidth),
                connectorHeight, connectorWidth);
    }

    private void printPipeR(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect((this.getWidth()-pipeHeight), middlePosition((int) (this.getHeight() * 0.25)),
                pipeHeight,  pipeWidth );

        g.setColor(Color.PINK);
        g.fillRect((this.getWidth()-connectorHeight), middlePosition(connectorWidth),
                connectorHeight, connectorWidth);
    }

    private void printPipeU(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), 0,
                pipeWidth, pipeHeight);

        g.setColor(Color.PINK);
        g.fillRect(middlePosition(connectorWidth), 0,
                connectorWidth, connectorHeight);
    }

    private void printPipeD(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), middlePosition(pipeWidth),
                pipeWidth, pipeHeight);

        g.setColor(Color.ORANGE);
        g.fillRect(middlePosition(connectorWidth), (this.getHeight()-connectorHeight),
                connectorWidth, connectorHeight);
    }

    private void rotate(boolean[] arr){
        boolean temp = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i+1];
        }
        arr[arr.length - 1] = temp;
    }



}
