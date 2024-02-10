package Assignments.A1.solving_algorithms;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.Move;
import Assignments.A1.models.Pair;
import Assignments.A1.resources.Parameters;
import org.junit.experimental.theories.internal.ParameterizedAssertionError;

import java.util.*;

public class DFS {

    private final Board solved = new Board();
    private final List<String> tried = new ArrayList<>();

    /* Commented out for future reference. */
//    public Board dfs(Board root, int depth, ArrayList<String> visited) {
//        counter++;
//        if (root.equals(solved)) {
//            return root;
//        }
//
//        ArrayList<String> directParents = new ArrayList<>(visited);
//        if (depth == Parameters.MAX_DEPTH || visited.contains(root.toString()) || tried.contains(root.toString())) {
//            return null;
//        }
//        directParents.add(root.toString());
//        tried.add(root.toString());
//
//        List<Move> moves = root.getMoves();
//        int moveNum = 1;
//        for (Move next : moves) {
//
//            Board child = next.getBoard();
//            child.swap(next);
//            moveNum++;
//            Board board = dfs(child, depth+1, directParents);
//            if (board != null) {
//                return board;
//            }
//        }
//        return null;
//    }

    public Board traverse(Board root, int depth) {
        Stack<Board> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Board current = stack.pop();
            if (current.equals(solved)) {
                return current;
            }

            if (depth == Parameters.MAX_DEPTH || tried.contains(current.toString())) {
                continue;
            }
            tried.add(current.toString());

            List<Move> moves = current.getMoves();
            for (Move next : moves) {
                Board child = next.getBoard();
                child.swap(next);
                stack.push(child);
            }
        }
        return null;
    }
}
