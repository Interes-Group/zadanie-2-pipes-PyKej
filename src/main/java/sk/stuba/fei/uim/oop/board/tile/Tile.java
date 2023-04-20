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

    @Getter
    private int posX;
    @Getter
    private int posY;
    private int middlePosition;

    @Setter
    private boolean highlight;

    public Tile(Pipe pipe, Direction direction1, Direction direction2, int posX, int posY) {
        this.direction1 = direction1;
        this.direction2 = direction2;
        this.pipe = pipe;

        this.visited = false;

        this.posX = posX;
        this.posY = posY;

//        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLUE);
//        System.out.println("fockjeeee");

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

        }

//


    }

    private int middlePosition(int width){
        int middle = (int) ((this.getWidth() * 0.5)- width *0.5);
        return middle;
    }




    public void rotate(){

        System.out.println(this.getPipe() + " "+ this.getPosX() + " "+ this.getPosY());
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
        if (pipe == Pipe.END || pipe == Pipe.START){
            direction2 = Direction.NONE;
        }


    }



//    {// predloha
//        public class RandomizedDFS {
//            private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
//            private static final Random rand = new Random();
//
//            public static void main(String[] args) {
//                int[][] maze = generateMaze(10, 10); // generate a 10x10 maze
//                printMaze(maze);
//            }
//
//            public static int[][] generateMaze(int numRows, int numCols) {
//                int[][] maze = new int[numRows][numCols];
//                Stack<Integer> stack = new Stack<>();
//                int totalCells = numRows * numCols;
//                int visitedCells = 1;
//                int row = 0, col = 0;
//
//                while (visitedCells < totalCells) {
//                    List<Integer> neighbors = new ArrayList<>();
//                    for (int[] dir : DIRECTIONS) {
//                        int newRow = row + dir[0];
//                        int newCol = col + dir[1];
//                        if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols && maze[newRow][newCol] == 0) {
//                            neighbors.add(newRow * numCols + newCol);
//                        }
//                    }
//
//                    if (!neighbors.isEmpty()) {
//                        int neighbor = neighbors.get(rand.nextInt(neighbors.size()));
//                        int neighborRow = neighbor / numCols;
//                        int neighborCol = neighbor % numCols;
//                        maze[row][col] |= 1 << rand.nextInt(4); // mark the wall as visited
//                        maze[neighborRow][neighborCol] |= 1 << ((DIRECTIONS.indexOf(Arrays.asList(neighborRow - row, neighborCol - col)) + 2) % 4); // mark the opposite wall as visited
//                        stack.push(row * numCols + col);
//                        row = neighborRow;
//                        col = neighborCol;
//                        visitedCells++;
//                    } else if (!stack.isEmpty()) {
//                        int cell = stack.pop();
//                        row = cell / numCols;
//                        col = cell % numCols;
//                    } else {
//                        // backtrack to a random cell
//                        int cell = rand.nextInt(totalCells);
//                        row = cell / numCols;
//                        col = cell % numCols;
//                    }
//                }
//
//                return maze;
//            }
//
//            public static void printMaze(int[][] maze) {
//                for (int[] row : maze) {
//                    for (int cell : row) {
//                        System.out.print((cell & 1) == 0 ? "##" : "# "); // print top wall
//                        System.out.print((cell & 2) == 0 ? "##" : "  "); // print right wall
//                    }
//                    System.out.println("#"); // print rightmost wall of each row
//                    for (int cell : row) {
//                        System.out.print((cell & 4) == 0 ? "# " : "  "); // print bottom wall
//                        System.out.print((cell & 8) == 0 ? "##" : "# "); // print left wall
//                    }
//                    System.out.println("#"); // print rightmost wall of each row
//                }
//            }
//        }
//
//    }



}
