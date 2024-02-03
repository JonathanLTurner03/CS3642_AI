package Assignments.A1.models;

import Assignments.A1.models.Board;
import Assignments.A1.models.Piece;

import java.util.*;

/**
 * This class is used to create and check boards.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class BoardGenerator {

    /**
     * Generates a random 8-Puzzle that is always solvable.
     *
     * @precondition none
     * @postcondition none
     *
     * @return a board that is solvable.
     */
    public static Board generateBoard() {

        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> random = new ArrayList<>(Arrays.asList(values));
        Collections.shuffle(random);

        Random gen = new Random();
        int spaceLoc = gen.nextInt(9);
        Piece[] pieces = new Piece[9];
        for (int curr = 0; curr < values.length; curr++) {
            if (curr < spaceLoc) {
                pieces[curr] = new Piece(curr,random.get(curr));
            } else {
                pieces[curr+1] = new Piece(curr+1,random.get(curr));
            }
        }

        // Checks if the board is solveable.
        Board generated = new Board(pieces);
        if (isSolvable(generated)) {
            return generated;
        } else { // If not it swaps the last two values (ignoring the space)
            if (spaceLoc == 8) {
                pieces[7].setLocation(6);
                pieces[6].setLocation(7);
            } else if (spaceLoc == 7) {
                pieces[8].setLocation(6);
                pieces[6].setLocation(8);
            } else {
                pieces[7].setLocation(6);
                pieces[6].setLocation(7);
            }
            generated = new Board(pieces);
        }

        // Puzzle is now solvable.
        return generated;
    }

    /**
     * Checks if the board is solvable by checking the number of inversions.
     * If the number of inversions is even, it is solvable, if not it is not solvable.
     *
     * @precondition board != null
     * @postcondition none
     *
     * @param board the board being checked
     * @return if the board has even inversion, True
     *         if not,                          False
     */
    public static boolean isSolvable(Board board) {
        if (board == null) {
            return false;
        }

        // Holds the number of inversions
        int inversions = 0;
        Piece[] ordered = board.getPiecesInOrder();

        // Counts the number of inversions
        for (int index = 0; index < 8; index++) {
            for (int invers = index+1; invers < 8; invers++) {
                if (ordered[index].getValue() > ordered[invers].getValue()) {
                    inversions++;
                }
            }
        }
        return (inversions % 2 == 0);
    }


}
