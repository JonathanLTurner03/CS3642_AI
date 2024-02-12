package Assignments.A1.solving_algorithms.comparators;

import Assignments.A1.models.BoardNode;
import java.util.Comparator;

/**
 * Is used to compare two BoardNodes for an BFS Traversal.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class BFS implements Comparator<BoardNode> {

    /**
     * Compares two BoardNodes and sorts them in respect to h(n) resulting in BFS Priority.
     *
     * @precondition o1 != null && o2 != null
     * @postcondition none
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return -1,0,1 based on the h(n).
     */
    @Override
    public int compare(BoardNode o1, BoardNode o2) {
        return Integer.compare(o1.heuristic, o2.heuristic);
    }
}

