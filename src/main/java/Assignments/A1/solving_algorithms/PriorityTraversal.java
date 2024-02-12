package Assignments.A1.solving_algorithms;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardNode;
import Assignments.A1.models.helper.Move;
import Assignments.A1.models.helper.Solver;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityTraversal implements Solver {

    private final Board solved = new Board();
    private final HashSet<Board> visited = new HashSet<>();

    private final Comparator<BoardNode> comparator;

    public PriorityTraversal(Comparator<BoardNode> type) {
        this.comparator = type;
    }

    public BoardNode traverse(Board root) {
        visited.clear();
        PriorityQueue<BoardNode> boards = new PriorityQueue<>(comparator);
        boards.add(new BoardNode(root, null));
        BoardNode node = null;
        Board current = new Board(root);
        while (!current.equals(solved) && !boards.isEmpty()) {
            node = boards.poll();
            current = node.board;
            if (visited.contains(node.board)) {
                continue;
            }
            visited.add(node.board);
            List<Move> children = node.board.getMoves();
            for (Move move : children) {
                Board child = new Board(node.board);
                child.swap(move);
                BoardNode childNode = new BoardNode(child, node);
                boards.add(childNode);
                node.addChild(childNode);
            }
        }
        return node;
    }
}
