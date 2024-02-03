package Assignments.A1.models;

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

        // Creates the initial board with default values.
        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> random = new ArrayList<>(Arrays.asList(values));
        Collections.shuffle(random);

        Random gen = new Random();
        int spaceLoc = gen.nextInt(9);
        int[] pieces = new int[9];
        for (int curr = 0; curr < values.length; curr++) {
            pieces[curr] = random.get(curr);

        }

        // Checks if the board is solvable.
        Board generated = new Board(pieces);
        if (isSolvable(generated)) {
            return generated;
        } else { // If not it swaps the last two values (ignoring the space)
            if (spaceLoc == 8) {
                generated.swap(6,7);
            } else if (spaceLoc == 7) {
                generated.swap(6,8);
            } else {
                generated.swap(7,8);
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
        int[] ordered = board.getPieces();

        // Counts the number of inversions
        for (int index = 0; index < 8; index++) {
            for (int invers = index+1; invers < 8; invers++) {
                if (ordered[index] > ordered[invers]) {
                    inversions++;
                }
            }
        }
        return (inversions % 2 == 1);
    }


}
