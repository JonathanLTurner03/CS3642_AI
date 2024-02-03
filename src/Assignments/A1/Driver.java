package Assignments.A1;

// Potentially will be changed to an UI Implementation with JavaFX if time permits.


import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.Board;
import Assignments.A1.models.Piece;

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
        Board board = BoardGenerator.generateBoard();
        System.out.println(board);
        System.out.println(BoardGenerator.isSolvable(board));

        Piece[] pieces = new Piece[9];
        pieces[0] = new Piece(0,7);
        pieces[1] = null;
        pieces[2] = new Piece(2,5);
        pieces[3] = new Piece(3,1);
        pieces[4] = new Piece(4,2);
        pieces[5] = new Piece(5,4);
        pieces[6] = new Piece(6,6);
        pieces[7] = new Piece(7,3);
        pieces[8] = new Piece(8,8);

        board = new Board(pieces);
        System.out.println(BoardGenerator.isSolvable(board));
    }

}
