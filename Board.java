/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Board {

    private int[][] board = new int[3][3];

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
    board = tiles;
    }

    // string representation of this board
    public String toString(){
        String boardString = "";

        for(int [] x : board){
            for(int y : x){
                boardString += y + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }

    // board dimension n
    public int dimension(){
        return board.length;
    }

    // number of tiles out of place
    public int hamming(){
        int counter = 0;
        int [][] goalArray = board.clone();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
               if((board[i][j] != i * board.length + j + 1)) counter++;
            }
        }
        if(board[board.length-1][board.length-1] == 0) counter--;

        return counter;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){

        int manhattan = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 0){
                    continue;
                }

                manhattan += Math.abs((board[i][j] - 1)/board.length) + Math.abs(((board[i][j] - 1)%board.length) - j);
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal(){
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
    public boolean equals(Object y){
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        return neighbors();
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        return twin();
    }

    // unit testing (not graded)
    public static void main(String[] args){

    }
     //   i
     // j{0,0},{0,1},{0,2}     1,2,3    i * board.length + j + 1           for(int i = 0; i < board.length; i++)
     //  {1,0},{1,1},{1,2}     4,5,6                                           for(int j = 0; j < board[i].length; j++)
     //  {2,0},{2,1},{2,2}     7,8,9                                                   board[i][j] = i* board.length + j + 1
     //                                                                    board[board.length-1][board.length - 1] = 0


}
