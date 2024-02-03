package Assignments.A1.models;

import java.util.Arrays;

/**
 * This class keeps track of the current state (whether in permutation or not) of the board.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class Board {

    private Piece[] pieces;

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
     * @precondition board != null
     * @postcondition a board is created with given locations
     *
     * @param board the provided state of the board.
     */
    public Board(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("The board must be valid an initialized.");
        }
        this.pieces = new Piece[9];
        for (int index = 0; index < 9; index++) {
            if (board.getPiece(index) != null) {
                this.pieces[index] = new Piece(board.getPiece(index));
            }
        }
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

    /**
     * Takes two values on the board and swaps them if possible.
     *
     * @precondition board.get(first).validMove(second) == true
     *            || board.get(second).validMove(first) == true
     * @postcondition the values are swapped
     *
     * @param first the first index of the values being swapped
     * @param second the second index of the values being swapped
     * @return if the values were swapped or not.
     */
    public boolean swap(int first, int second) {
        // Checks if the first piece is empty and the second is not.
        if (this.pieces[first] == null && this.pieces[second] != null) {
            // Checks if the moves are valid
            if (this.pieces[second].validMove(first, this)) {
                // Swaps the values
                this.pieces[first] = this.pieces[second];
                this.pieces[second] = null;
                return true; // Successful Swap
            }
        } else if (this.pieces[second] == null && this.pieces[first] != null) { // Checks the inverse.
            // Checks if the moves are valid
            if (this.pieces[first].validMove(second, this)) {
                // Swaps the values
                this.pieces[second] = this.pieces[first];
                this.pieces[first] = null;
                return true; // Successful Swap
            }
        }
        return false;
    }

    /**
     * Hashes the board by adding the pieces to an array and hashes the array.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the hash of the board.
     */
    @Override
    public int hashCode() {
        int[] hashValues = new int[9];

        for (int i = 0; i < 9; i++) {
            if (this.pieces[i] != null) {
                hashValues[i] = this.pieces[i].getValue();
            } else {
                hashValues[i] = 0;
            }
        }

        return Arrays.hashCode(hashValues);
    }

    /**
     * Checks if this and the other object are the same.
     * @precondition none
     * @postcondition they are compared.
     *
     * @param o the other object.
     * @return if they are the same, True
     *         if they are not,      False
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Board other = (Board) o;
        for (int i = 0; i < 9; i++) {
            if (this.pieces[i].getValue() != other.pieces[i].getValue()
                || this.pieces[i].getLoc() != other.pieces[i].getLoc()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Specifies a way of printing out the board with its current state.
     * @return the current state formatted.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 9; i++) {
            result += i + " ";
            if ((i+1) % 3 == 0) {
                result += "\n";
            }
        }
        return result;
    }

    /* Private Methods Below. End of Java Docs. */

    /* Checks if a given location is a valid location. */
    private boolean invalidLocation(int loc) {
        return (loc < 0 || loc > 8);
    }
}