package Assignments.A1.models.helper;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardNode;

/**
 * Interface used for implementing a solver when there are multiple different variations and classes.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public interface Solver {

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
