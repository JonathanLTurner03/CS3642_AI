package Assignments.A1_8Puzzles.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to represent a board on a spanning tree.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class BoardNode implements Comparable<BoardNode> {

    /* Fields used to hold each node's data. */
    public BoardNode parent;
    public Board board;
    public List<BoardNode> children;
    public int heuristic, cost, expected, depth;

    /**
     * Used to create a board with a parent and child nodes.
     *
     * @precondition none
     * @postcondition none
     *
     * @param board the board representing the node.
     * @param parent the parent node, can be null.
     */
    public BoardNode(Board board, BoardNode parent) {
        this.board = board;
        this.parent = parent;
        this.heuristic = this.getHeuristic();
        this.cost = this.getActualCost();
        this.expected = this.cost + this.expected;
        this.children = new ArrayList<>();
    }

    /**
     * adds a child to the node.
     *
     * @precondition child != null
     * @postcondition the child is added..
     */
    public void addChild(BoardNode node) {
        children.add(node);
    }

    /* Overrides */

    /**
     * Overrides the Object compareTo method. Is a placehold for specifying priority in queues.
     * Default: BFS
     *
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(BoardNode o) { // BFS
        return Integer.compare(this.heuristic, o.heuristic);
    }

    /**
     * Represents the board in a single string (no line breaks)
     * @return the formatting string
     */
    @Override
    public String toString() {
        return this.board.toString().replaceAll("\n", " ");
    }

    /* Private Classes: End of JavaDocs */

    private int getActualCost() {
        if (this.parent != null) {
            return this.parent.cost + 10;
        } else {
            return 0;
        }
    }

    private int getHeuristic() {
        int cost = 0;
        for (int i = 0; i < 9; i++) {
            int currValue = board.getPiece(i);
            int idealIndex = idealIndex(currValue);
            int currRow = i / 3;
            int currCol = i % 3;
            int idealRow = idealIndex / 3;
            int idealCol = idealIndex % 3;
            cost += Math.abs(idealRow - currRow) + Math.abs(idealCol - currCol);
        }
        return cost;
    }

    private int idealIndex(int value) {
        int result = -1;
        switch (value) {
            case 0:
                result = 4;
                break;
            case 1:
                result = 0;
                break;
            case 2:
                result = 1;
                break;
            case 3:
                result = 2;
                break;
            case 4:
                result = 5;
                break;
            case 5:
                result = 8;
                break;
            case 6:
                result = 7;
                break;
            case 7:
                result = 6;
                break;
            case 8:
                result = 3;
                break;
        }
        return result;
    }
}
