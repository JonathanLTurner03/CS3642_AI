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

        this.moveTo(loc);
        this.value = value;
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
        if (newLoc < 0 || newLoc > 8) {
            throw new IllegalArgumentException("The new location is not valid.");
        }
        this.loc = newLoc;
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
}
