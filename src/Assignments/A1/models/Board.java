package Assignments.A1.models;

/**
 * This class keeps track of the current state (whether in permutation or not) of the board.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class Board {

    private final Piece[] pieces;

    /**
     * Default constructor that generates the solved board.
     *
     * @precondition none
     * @postcondition a solved board is created.
     */
    public Board() {
        this.pieces = new Piece[9];
        for (int index = 1; index < 9; index++) {
            this.pieces[index-1] = new Piece(index-1, index);
        }
    }

    /**
     * Constructor used to create a board with a pre-provided board.
     *
     * @precondition board.length == 9
     * @postcondition a board is created with given locations
     *
     * @param board the provided state of the board.
     */
    public Board(Piece[] board) {
        if (board.length != 9) {
            throw new IllegalArgumentException("The board must be a array size of 9.");
        }
        this.pieces = board;
    }

    /**
     * Checks the status of a place on the board. If the location is taken or not.
     *
     * @precondition loc >= 0 & loc <= 8
     * @postcondition none
     * @param loc the location being checked.
     *
     * @return If the location has a piece, True
     *         If the location is null,     False
     */
    public boolean isTaken(int loc) {
        if (invalidLocation(loc)) {
            throw new IllegalArgumentException("Invalid location specified. Valid Range: 0 <= Location <= 8");
        }
        return (this.pieces[loc].getValue() != 0);
    }

    /**
     * Returns a value in the array of pieces.
     *
     * @precondition loc >= 0 & loc <= 8
     * @postcondition none
     * @param loc the location of the desired piece.
     *
     * @return the array of pieces.
     */
    public Piece getPiece(int loc) {
        if (invalidLocation(loc)) {
            throw new IllegalArgumentException("Invalid location specified. Valid Range: 0 <= Location <= 8");
        }
        return pieces[loc];
    }

    /**
     * Checks and searches for the location of the open location and returns the index.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the location of the empty piece.
     */
    public int getOpenLocation() {
        for (int index = 0; index < 9; index++) {
            if (this.pieces[index] == null) {
                return index;
            }
        }
        throw new IllegalStateException("The state of the board must have 1 empty space.");
    }


    /* Private Methods Below. End of Java Docs. */

    /* Checks if a given location is a valid location. */
    private boolean invalidLocation(int loc) {
        return (loc >= 0 && loc <= 8);
    }
}
