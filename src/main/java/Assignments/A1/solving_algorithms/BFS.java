package Assignments.A1.solving_algorithms;

import Assignments.A1.models.Board;
import Assignments.A1.models.BoardNode;
import Assignments.A1.models.Move;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class BFS {

    private final HashSet<Board> visited = new HashSet<>();
    private static final Board solved = new Board();

    public BoardNode traverse(Board root) {
        PriorityQueue<BoardNode> boards = new PriorityQueue<>(new BFSPriority());
        HashSet<Board> visited = new HashSet<>();
        boards.add(new BoardNode(root, null));
        BoardNode node = null;
        Board current = new Board(root);
        while (!current.equals(solved)) {
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

class BFSPriority implements Comparator<BoardNode> {
    @Override
    public int compare(BoardNode o1, BoardNode o2) {
        return Integer.compare(o1.heuristic, o2.heuristic);
    }
}

