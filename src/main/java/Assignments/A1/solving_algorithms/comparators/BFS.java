package Assignments.A1.solving_algorithms.comparators;

import Assignments.A1.models.BoardNode;

import java.util.Comparator;

public class BFS implements Comparator<BoardNode> {
    @Override
    public int compare(BoardNode o1, BoardNode o2) {
        return Integer.compare(o1.heuristic, o2.heuristic);
    }
}

