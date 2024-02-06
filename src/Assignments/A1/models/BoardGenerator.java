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
        Board ideal = new Board();
        Random random = new Random();
        int numOfMoves = random.nextInt(30,50);
        for (int i = 0; i < numOfMoves; i++) {
            List<Move> moves = ideal.getMoves();
            Move randomMove = moves.get(random.nextInt(moves.size()));
            ideal.swap(randomMove);
        }
        return ideal;
    }
}
