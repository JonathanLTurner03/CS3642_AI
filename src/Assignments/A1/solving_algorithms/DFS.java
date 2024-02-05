package Assignments.A1.solving_algorithms;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.Move;
import Assignments.A1.models.Pair;
import Assignments.A1.resources.Parameters;
import org.junit.experimental.theories.internal.ParameterizedAssertionError;

import java.util.*;

public class DFS {

    private int counter = 0;
    private final Board solved = new Board();
    private List<String> tried = new ArrayList<>();

    public Board dfs(Board root, int depth, ArrayList<String> visited) {
        counter++;
        System.out.println("Num of boards " + counter + " | Depth " + depth);
        if (root.equals(solved)) {
            return root;
        }

        ArrayList<String> directParents = new ArrayList<>(visited);
        if (depth == Parameters.MAX_DEPTH || visited.contains(root.toString()) || tried.contains(root.toString())) {
            return null;
        }
        directParents.add(root.toString());
        tried.add(root.toString());

        List<Move> moves = root.getMoves();
        int moveNum = 1;
        for (Move next : moves) {

            Board child = next.getBoard();
            child.swap(next);
            moveNum++;
            Board board = dfs(child, depth+1, directParents);
            if (board != null) {
                return board;
            }
        }
        return null;
    }

}
