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

    public boolean traverse(Board root) {
        BoardNode current = new BoardNode(root);
        Stack<BoardNode> lvr = new Stack<>();
        lvr.push(current);
        while (!lvr.isEmpty()) {
            BoardNode curr = lvr.pop();
            if (curr.board.equals(solved)) {
                return true;
            }
            List<Move> moves = curr.board.getMoves();
            for (Move move : moves) {
                Board child = new Board(curr.board);
                child.swap(move);
                if (!curr.hasAncestor(child)) {
                    BoardNode childNode = new BoardNode(child, curr);
                    curr.addChild(childNode);
                    lvr.add(childNode);
                }
            }
        }
        return false;

    }




}

class BoardNode {

    public Board board;
    public BoardNode parent;
    public List<BoardNode> children;

    public BoardNode(Board child, BoardNode parent) {
        this.board = child;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public BoardNode (Board child) {
        this.board = child;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public boolean hasAncestor(Board board) {
        BoardNode current = this;
        while (current.parent != null) {
            current = current.parent;
            if (current.board.equals(board)) {
                return true;
            }
        }
        return false;
    }

    public void addChild(BoardNode child) {
        if (child != null) {
            children.add(child);
        }
    }

}
