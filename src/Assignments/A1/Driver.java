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
        Board board = BoardGenerator.generateBoard();
        Board result = solver.dfs(board, 0, new ArrayList<>());
        int count = 0;
        while (count != 100 || result != null) {
            result = solver.dfs(board, 0, new ArrayList<>());
            System.out.println(count);
            count++;
            board = BoardGenerator.generateBoard();
        }
    }

}
