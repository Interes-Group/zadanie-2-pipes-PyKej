package sk.stuba.fei.uim.oop.board.tile;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    @Setter @Getter
    private Pipe pipe;

    @Setter @Getter
    private Direction direction1;
    @Setter @Getter
    private Direction direction2;

    private int pipeWidth;
    private int pipeHeight;
    private int connectorWidth;
    private int connectorHeight;

    @Getter @Setter
    private boolean visited;

    @Getter @Setter
    private boolean water;

    @Getter
    private int posX;
    @Getter
    private int posY;
    private int middlePosition;

    @Setter
    private boolean highlight;

    public static final int BACKGROUND_TILE = 0xF2AE4E;
    public static final int START_BACKGROUND = 0x3EBFA1;
    public static final int END_BACKGROUND = 0x8CC600;
    public static final int MIDDLE_PIPE = 0xA0090B;
    public static final int CONNECTOR_PIPE = 0xBF220B;
    public static final int WATER_MIDDLE_PIPE = 0x4674B3;
    public static final int WATER_CONNECTOR_PIPE = 0x6280FC;

    public Tile(int posX, int posY) {
        this.direction1 = Direction.NONE;
        this.direction2 = Direction.NONE;
        this.pipe = Pipe.NONE;
        this.visited = false;
        this.posX = posX;
        this.posY = posY;

        this.setBackground(new Color(BACKGROUND_TILE));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int pipeMiddle;
        int pipeConnector;

        if (isWater()){
            pipeMiddle = WATER_MIDDLE_PIPE;
            pipeConnector = WATER_CONNECTOR_PIPE;
        }
        else {
            pipeMiddle = MIDDLE_PIPE;
            pipeConnector = CONNECTOR_PIPE;
        }

        if (pipe == Pipe.START){
            this.setBackground(new Color(START_BACKGROUND));
        }
        else if (pipe == Pipe.END){
            this.setBackground(new Color(END_BACKGROUND));
        }

        if (this.highlight) {
            g.setColor(Color.GREEN);
            ((Graphics2D) g).setStroke(new BasicStroke(3));
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            this.highlight = false;
        }
        else {
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3));
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
        }

            this.pipeWidth = (int) (this.getWidth() * 0.25);
            this.pipeHeight = (int) ((this.getWidth() * 0.5)+ pipeWidth *0.5);
            this.connectorWidth = (int) (this.getWidth() * 0.35);
            this.connectorHeight = (int) (this.getHeight() * 0.08);
            this.middlePosition =(int) ((this.getWidth() * 0.5)-(this.getWidth() * 0.25)*0.5);

        if(direction1 == Direction.L || direction2 == Direction.L){
            g.setColor(new Color(pipeMiddle));
            g.fillRect(0, middlePosition((int) (this.getHeight() * 0.25)),
                    pipeHeight,  pipeWidth );

            g.setColor(new Color(pipeConnector));
            g.fillRect(0, middlePosition(connectorWidth),
                    connectorHeight, connectorWidth);
        }

        if(direction1 == Direction.U || direction2 == Direction.U){
            g.setColor(new Color(pipeMiddle));
            g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), 0,
                    pipeWidth, pipeHeight);

            g.setColor(new Color(pipeConnector));
            g.fillRect(middlePosition(connectorWidth), 0,
                    connectorWidth, connectorHeight);
        }

        if(direction1 == Direction.R || direction2 == Direction.R){
            g.setColor(new Color(pipeMiddle));
            g.fillRect((this.getWidth()-pipeHeight), middlePosition((int) (this.getHeight() * 0.25)),
                    pipeHeight,  pipeWidth );

            g.setColor(new Color(pipeConnector));
            g.fillRect((this.getWidth()-connectorHeight), middlePosition(connectorWidth),
                    connectorHeight, connectorWidth);
        }

        if(direction1 == Direction.D || direction2 == Direction.D){
            g.setColor(new Color(pipeMiddle));
            g.fillRect(middlePosition((int) (this.getWidth() * 0.25)), middlePosition(pipeWidth),
                    pipeWidth, pipeHeight);

            g.setColor(new Color(pipeConnector));
            g.fillRect(middlePosition(connectorWidth), (this.getHeight()-connectorHeight),
                    connectorWidth, connectorHeight);
        }
    }

    private int middlePosition(int width){
        int middle = (int) ((this.getWidth() * 0.5)- width *0.5);
        return middle;
    }

    public void rotate(){
        Direction[] directions = Direction.values();

        int dir1Index = 1+ direction1.ordinal();
        int dir2Index = 1+ direction2.ordinal();

        if (dir1Index >= directions.length-1){
            dir1Index =0;
        }
        if (dir2Index >= directions.length-1){
            dir2Index =0;
        }

        direction1 = directions[dir1Index];
        direction2 = directions[dir2Index];
        if (pipe == Pipe.END){
            direction2 = Direction.NONE;
        }
        else if (pipe == Pipe.START) {
            direction1 = Direction.NONE;
        }
        else if (pipe == Pipe.NONE) {
            direction1 = Direction.NONE;
            direction2 = Direction.NONE;
        }
    }

}
