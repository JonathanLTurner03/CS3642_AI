package Assignments.A1.solving_algorithms;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardGenerator;
import Assignments.A1.models.Move;
import Assignments.A1.models.Pair;
import Assignments.A1.resources.Parameters;

import java.util.*;

public class DFS {

    private final Board solved = new Board();
    private final List<String> tried = new ArrayList<>();

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
