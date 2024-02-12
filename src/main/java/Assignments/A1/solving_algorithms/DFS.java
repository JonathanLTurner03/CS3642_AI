package Assignments.A1.solving_algorithms;

import Assignments.A1.models.*;
import Assignments.A1.models.helper.Move;
import Assignments.A1.models.helper.Solver;
import Assignments.A1.resources.Parameters;
import java.util.*;

/**
 * Used to evaluate a generated SpanningTree of BoardNodes with respect to DFS LIFO.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class DFS implements Solver {

    /* Private Fields for Searching */
    private final Board solved = new Board();
    private final List<String> tried = new ArrayList<>();

    /**
     * Traverses through a Board using a Stack, allowing for DFS LIFO.
     *
     * @precondition root != null
     * @postcondition the spanning tree is created.
     *
     * @param root the first node/initial start.
     * @return the leaf node that is solved.
     */
    public BoardNode traverse(Board root) {
        tried.clear(); // Resets each run.
        Stack<BoardNode> stack = new Stack<>();
        BoardNode rootNode = new BoardNode(root, null);
        rootNode.depth = 0;
        stack.push(rootNode);

        while (!stack.isEmpty()) {
            BoardNode current = stack.pop();

            if (current.board.equals(solved)) {
                return current;
            }

            if (current.depth > Parameters.MAX_DEPTH || tried.contains(current.toString())) {
                continue;
            }
            tried.add(current.toString());

            List<Move> moves = current.board.getMoves();
            for (Move next : moves) {
                Board child = next.getBoard();
                child.swap(next);
                BoardNode childNode = new BoardNode(child, current);
                childNode.depth = current.depth+1;
                stack.push(childNode);
                current.addChild(childNode);
            }
        }
        return null;
    }
}
