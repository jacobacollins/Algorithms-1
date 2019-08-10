import java.util.LinkedList;
import java.util.List;

/******************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *****************************************************************************/

public class Board {

    private int[][] board = new int[3][3];
    private int emptyX, emptyY;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        board = tiles;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (board[i][j] == 0) {
                    emptyX = i;
                    emptyY = j;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(board.length).append("\n");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                builder.append(String.format("%2d ", board[row][col]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return board.length;
    }

    // number of tiles out of place
    public int hamming() {
        int counter = 0;
        int[][] goalArray = board.clone();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((board[i][j] != i * board.length + j + 1)) counter++;
            }
        }
        --counter;

        return counter;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int result = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (row == emptyX && col == emptyY) {
                    continue;
                }
                result += manhattan(row, col);
            }
        }
        return result;
    }

    private int manhattan(int row, int col) {
        int destVal = board[row][col] - 1;
        int destRow = destVal / board.length;
        int destCol = destVal % board.length;
        return Math.abs(destRow - row) + Math.abs(destCol - col);
    }

    // is this board the goal board?
    public boolean isGoal() {
        // int [][] goalArray = board.clone();
        // for(int i = 0; i < goalArray.length; i++){
        //     for(int j = 0; j < goalArray[i].length; j++){
        //         board[i][j] = i * goalArray.length + j + 1;
        //     }
        // }
        // goalArray[goalArray.length-1][goalArray.length-1] = 0;

        // return board == goalArray;
        return hamming() == 0;
    }


    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (this != that) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (this.board[i][j] != that.board[i][j]) return false;

            }
        }
        return true;
    }

    private void createNeighborBoards(int[][] boardCopy, int x1, int y1, int x2, int y2) {
        int swapPosition = boardCopy[x1][y1];
        boardCopy[x1][y1] = boardCopy[x2][y2];
        boardCopy[x2][y2] = swapPosition;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        List<Board> neighbors = new LinkedList<>();

        if (emptyX > 0) {
            int[][] aboveBoard = board.clone();
            createNeighborBoards(aboveBoard, emptyX, emptyY, emptyX - 1, emptyY);
            neighbors.add(new Board(aboveBoard));
        }
        if (emptyX < board.length - 1) {
            int[][] belowBoard = board.clone();
            createNeighborBoards(belowBoard, emptyX, emptyY, emptyX + 1, emptyY);
            neighbors.add(new Board(belowBoard));
        }
        if (emptyY > 0) {
            int[][] rightBoard = board.clone();
            createNeighborBoards(rightBoard, emptyX, emptyY, emptyX, emptyY - 1);
            neighbors.add(new Board(rightBoard));
        }
        if (emptyY < board.length - 1) {
            int[][] leftBoard = board.clone();
            createNeighborBoards(leftBoard, emptyX, emptyY, emptyX, emptyY + 1);
            neighbors.add(new Board(leftBoard));
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] cloned = board.clone();
        if (emptyX != 0) {
            createNeighborBoards(cloned, 0, 0, 0, 1);
        }
        else {
            createNeighborBoards(cloned, 1, 0, 1, 1);
        }
        return new Board(cloned);
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
    //   i
    // j{0,0},{0,1},{0,2}     1,2,3    i * board.length + j + 1           for(int i = 0; i < board.length; i++)
    //  {1,0},{1,1},{1,2}     4,5,6                                           for(int j = 0; j < board[i].length; j++)
    //  {2,0},{2,1},{2,2}     7,8,9                                                   board[i][j] = i* board.length + j + 1
    //                                                                    board[board.length-1][board.length - 1] = 0


}
