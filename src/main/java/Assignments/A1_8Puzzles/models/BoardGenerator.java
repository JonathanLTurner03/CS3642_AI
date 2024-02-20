package Assignments.A1_8Puzzles.models;

import Assignments.A1_8Puzzles.models.helper.Move;

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
        Board ideal = new Board();
        Random random = new Random();
        do {
            int numOfMoves = random.nextInt(100, 150);
            for (int i = 0; i < numOfMoves; i++) {
                List<Move> moves = ideal.getMoves();
                Move randomMove = moves.get(random.nextInt(moves.size()));
                ideal.swap(randomMove);
            }
        } while (ideal.equals(new Board()));
        return ideal;
    }
}
