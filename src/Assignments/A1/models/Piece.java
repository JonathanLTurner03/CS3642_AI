package Assignments.A1.models;

/**
 * This class allows for abstraction and proper representation of a piece of the 8 puzzle.
 *
 * @author Jonathan Turner
 * @version CS3642 - Spring 2024
 */
public class Piece {

    private int loc;
    private final int value;

    /**
     * Creates a new piece with a specified location and value.
     *
     * @precondition loc >= 0 & newLoc <= 8
     *               & value >= 1 & value <= 8
     * @postcondition the piece is created.
     *
     * @param loc the location of the piece.
     * @param value the value of the piece. (FINAL)
     */
    public Piece(int loc, int value) {
        if (value < 1 || value > 8) {
            throw new IllegalArgumentException("The piece value is not valid. Valid Range: 1 <= Value <= 8");
        }
        this.setLoc(loc);
        this.value = value;
    }

    /**
     * Creates a new piece from an existing piece.
     *
     * @precondition piece != null
     * @postcondition a copied piece is created.
     *
     * @param piece the piece being copied
     */
    public Piece(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("The piece cannot be null.");
        }
        this.loc = piece.getLoc();
        this.value = piece.getValue();
    }

    /**
     * Moves the piece to a new location.
     *
     * @precondition newLoc >= 0 & newLoc <= 8
     * @precondition location = newLoc
     *
     * @param newLoc the new location of the piece.
     */
    public void moveTo(int newLoc) {
        if (newLoc == this.loc) {
            throw new IllegalArgumentException("The new location cannot be the same as the old location.");
        }
        this.setLoc(newLoc);
    }

    /**
     * Gets the location of the piece.
     *
     * @precondition None
     * @postcondition None
     *
     * @return loc the location of the piece.
     */
    public int getLoc() {
        return this.loc;
    }

    /**
     * Gets the value of the piece.
     *
     * @precondition None
     * @postcondition None
     *
     * @return value the Value of the piece.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Checks if the move to a location is valid.
     *
     * @precondition none
     * @postcondition none
     *
     * @param desiredLoc the location being checked if move is valid.
     * @param board the current board layout.
     * @return if the move is valid, True
     *         else,                 False
     */
    public boolean validMove(int desiredLoc, Board board) {
        if (desiredLoc != this.loc) {
            return false;
        }
        if (this.invalidLocation(desiredLoc)) {
            return false;
        }
        // Checks if the desiredLoc is above, to the left/right, or below.
        if (desiredLoc != this.loc-3 && desiredLoc != this.loc-1
                && desiredLoc != this.loc+1 && desiredLoc == this.loc+3) {
            return false;
        }
        return board.isTaken(desiredLoc);
    }

    /* Private Methods Below. End of Java Docs. */

    /* Sets the location for both Moving and Initialization. */
    private void setLoc(int loc) {
        if (this.invalidLocation(loc)) {
            throw new IllegalArgumentException("The new location is not valid. Valid Range: 0 <= Location <= 8.");
        }
        this.loc = loc;
    }

    /* Checks if a given location is a valid location. */
    private boolean invalidLocation(int loc) {
        return (loc < 0 || loc > 8);
    }
}
