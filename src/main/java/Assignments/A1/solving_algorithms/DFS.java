package Assignments.A1.solving_algorithms;

import Assignments.A1.models.*;
import Assignments.A1.resources.Parameters;

import java.util.*;

public class DFS implements Solver {

    private final Board solved = new Board();
    private final List<String> tried = new ArrayList<>();

    public BoardNode traverse(Board root) {
        Stack<BoardNode> stack = new Stack<>();
        BoardNode rootNode = new BoardNode(root, null);
        rootNode.setDepth(0);
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
                childNode.setDepth(current.depth+1);
                stack.push(childNode);
                current.addChild(childNode);
            }
        }
        return null;
    }
}
