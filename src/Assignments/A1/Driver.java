package Assignments.A1;

// Potentially will be changed to an UI Implementation with JavaFX if time permits.


import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.Board;
import Assignments.A1.models.Piece;
import Assignments.A1.solving_algorithms.DFS;

import java.util.ArrayList;

/**
 * Board will be used to save locations in a 2D array.
 *
 * The design of the board locations will be numbered like so:
 *      0   1   2
 *      3   4   5
 *      6   7   8
 *
 * The values will be specified with an int. Must be between (inclusive) 1-8.
 * The empty spot will be represented with null.
 */
public class Driver {

    public static void main(String[] args) {
        DFS solver = new DFS();
//        Board board = new Board();
//        Board result = solver.dfs(board, 0);
//        System.out.println(result);
//        Board board = BoardGenerator.generateBoard();
//
//        Board result = solver.dfs(board, 0, new ArrayList<String>());
//        System.out.println(result);
//
        int[] temp = {0,8,3,5,1,6,4,7,2};
        Board solvable = new Board(temp);
        Board result = solver.dfs(solvable,0, new ArrayList<>());
        System.out.println(result);
    }

}
