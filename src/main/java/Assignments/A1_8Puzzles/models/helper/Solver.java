package Assignments.A1_8Puzzles.models.helper;

import Assignments.A1_8Puzzles.models.Board;
import Assignments.A1_8Puzzles.models.BoardNode;

/**
 * Interface used for implementing a solver when there are multiple different variations and classes.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public interface Solver {

    /**
     * Returns the number of nodes from the previous iterations.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the number of nodes from the interation.
     */
    int getNumOfNodes();

    /**
     * Solves for, finds and creates a spanning representing the solving pattern.
     *
     * @precondition none
     * @postcondition none
     *
     * @param root the first node/initial start.
     * @return the solved leaf node.
     */
    BoardNode traverse(Board root);
}
