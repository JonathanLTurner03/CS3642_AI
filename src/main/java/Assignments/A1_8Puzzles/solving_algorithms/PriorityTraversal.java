package Assignments.A1_8Puzzles.solving_algorithms;

import Assignments.A1_8Puzzles.models.Board;
import Assignments.A1_8Puzzles.models.BoardNode;
import Assignments.A1_8Puzzles.models.helper.Move;
import Assignments.A1_8Puzzles.models.helper.Solver;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Used to traverse a Spanning Tree of 8 Puzzle using a provided comparitor for a Priority Queue.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class PriorityTraversal implements Solver {

    private final Board solved = new Board();
    private final HashSet<Board> visited = new HashSet<>();
    private final Comparator<BoardNode> comparator;
    private int numOfNodes = 1;

    public PriorityTraversal(Comparator<BoardNode> type) {
        this.comparator = type;
    }

    /**
     * Traverses through the list using a priority queue which is specified by the comparitor at constructor.
     *
     * @precondition root != null
     * @postcondition a spanning tree is created.
     *
     * @param root the first node/initial start.
     * @return the solved leaf node.
     */
    public BoardNode traverse(Board root) {
        visited.clear();
        PriorityQueue<BoardNode> boards = new PriorityQueue<>(comparator);
        boards.add(new BoardNode(root, null));
        BoardNode node = null;
        Board current = new Board(root);
        numOfNodes = 1;
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
                numOfNodes++;
            }
        }
        return node;
    }

    /**
     * Returns the number of nodes from the previous iterations.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the number of nodes from the interation.
     */
    @Override
    public int getNumOfNodes() {
        return this.numOfNodes;
    }
}
