package Assignments.A1.models;

/**
 * Used to keep track of moves in either a stack or queues.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class Move {

    private Board board;
    private final Pair<Integer> points;
    private boolean used;

    /**
     * Creates a new move with the given points.
     *
     * @precondition none
     * @postcondition a new move is created.
     * @param firstpoint the first point
     * @param secondpoint the second point
     * @param root the board attached to the move.
     */
    public Move(int firstpoint, int secondpoint, Board root) {
        this.points = new Pair<>(firstpoint, secondpoint);
        this.board = new Board(root);
    }

    /**
     * Checks if a move was used.
     * @precondition none
     * @postcondition none
     * @return whether the move was used.
     */
    public boolean used() {
        return used;
    }

    /**
     * Returns the points for the move and marks it as used.
     *
     * @precondition none
     * @postcondition used == true
     * @return the pair of points.
     */
    public Pair<Integer> traverse() {
        used = true;
        return points;
    }

    /**
     * Returns the board for the move.
     *
     * @precondition none
     * @postcondition none
     * @return the board.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Same as root
     */
    public boolean isRoot() {
        Board temp = new Board(this.board);
        temp.swap(this.points.first, this.points.second);
        if (temp.equals(this.board)) {
            return true;
        }
        return false;
    }
}

