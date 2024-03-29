package Assignments.A1_8Puzzles.solving_algorithms.comparators;

import Assignments.A1_8Puzzles.models.BoardNode;
import java.util.Comparator;

/**
 * Is used to compare two BoardNodes for an AStar Traversal.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class AStar implements Comparator<BoardNode> {

    /**
     * Compares two BoardNodes and sorts them in respect to both g(n) and h(n) resulting in AStar Priority.
     *
     * @precondition o1 != null && o2 != null
     * @postcondition none
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return -1,0,1 based on the a(n).
     */
    @Override
    public int compare(BoardNode o1, BoardNode o2) {
        return Integer.compare(o1.expected, o2.expected);
    }
}