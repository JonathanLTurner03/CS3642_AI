package Assignments.A1.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class keeps track of the current state (whether in permutation or not) of the board.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class Board {

    public int[] pieces;

    /**
     * Default constructor that generates the solved board.
     *
     * @precondition none
     * @postcondition a solved board is created.
     */
    public Board() {
        this.pieces = new int[9];
        this.pieces[0] = 1;
        this.pieces[1] = 2;
        this.pieces[2] = 3;
        this.pieces[3] = 8;
        this.pieces[5] = 4;
        this.pieces[6] = 7;
        this.pieces[7] = 6;
        this.pieces[8] = 5;
        this.pieces[4] = 0;
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
        this.pieces = new int[9];
        for (int index = 0; index < 9; index++) {
            this.pieces[index] = board.getPiece(index);
        }
    }

    /**
     * Constructor used to create a board with a pre-provided piece list.
     *
     * @precondition pieces == 9
     * @postcondition a board is created with given locations
     *
     * @param pieces the provided state.
     */
    public Board(int[] pieces) {
        if (pieces.length != 9) {
            throw new IllegalArgumentException("The pieces list must be size 9.");
        }
        this.pieces = pieces;
    }


    /**
     * Checks the status of a place on the board. If the location is taken or not.
     *
     * @precondition loc >= 0 & loc <= 8
     * @postcondition none
     * @param loc the location being checked.
     *
     * @return If the location has a piece, True
     *         If the location is 0,        False
     */
    public boolean isTaken(int loc) {
        if (invalidLocation(loc)) {
            throw new IllegalArgumentException("Invalid location specified. Valid Range: 0 <= Location <= 8");
        }
        return (this.pieces[loc] != 0);
    }

    /**
     * Returns a value in the array of pieces.
     *
     * @param loc the location of the desired piece.
     * @precondition loc >= 0 & loc <= 8
     * @postcondition none
     * @return the pieces at that location.
     */
    public int getPiece(int loc) {
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
        int loc = -1;
        for (int index = 0; index < 9; index++) {
            if (this.pieces[index] == 0) {
                loc = index;
            }
        }
        if (loc != -1) {
            return loc;
        } else {
            throw new IllegalStateException("The state of the board must have 1 empty space.");
        }
    }

    /**
     * Takes two values on the board and swaps them.
     *
     * @precondition none
     * @postcondition the values are swapped
     *
     * @param first the first index of the values being swapped
     * @param second the second index of the values being swapped
     */
    public void swap(int first, int second) {
        int temp = this.pieces[first];
        this.pieces[first] = this.pieces[second];
        this.pieces[second] = temp;
    }

    public void swap(Move move) {
        Pair<Integer> points = move.traverse();
        int temp = this.pieces[points.first];
        this.pieces[points.first] = this.pieces[points.second];
        this.pieces[points.second] = temp;
    }

    /**
     * Gets the pieces in order in a single array of size 8.
     *
     * @precondition none
     * @postcondition none
     *
     * @return the ordered list of values.
     */
    public int[] getPieces() {
        int[] ordered = new int[8];
        int iterations = 0;
        for (int curr : this.pieces) {
            if (curr == 0) {
                continue;
            }
            ordered[iterations] = curr;
        }
        return ordered;
    }

    /**
     * Adds all the possible moves the state can make based off the open space.
     *
     * @precondition none
     * @postcondition none
     * @return the list of possible moves.
     */
    public List<Move> getMoves() {
        List<Move> moves = new ArrayList<>();
        int space = getOpenLocation();
        int row = space / 3;
        int column = space % 3;

        if (column > 0) {
            moves.add(new Move(space, space-1, this));
        }
        if (column < 2) {
            moves.add(new Move(space, space+1, this));
        }
        if (row > 0) {
            moves.add(new Move(space, space-3, this));
        }
        if (row < 2) {
            moves.add(new Move(space, space+3, this));
        }
        return moves;
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
            hashValues[i] = this.pieces[i];
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
        return (other.toString().equals(this.toString()));
    }

    /**
     * Specifies a way of printing out the board with its current state.
     * @return the current state formatted.
     */
//    @Override
//    public String toString() {
//        String result = "";
//        for (int i = 0; i < 9; i++) {
//            result += pieces[i] + " ";
//            if ((i+1) % 3 == 0) {
//                result += "\n";
//            }
//        }
//        return result;
//    }
    @Override
    public String toString() {
        String result = pieces[0] + " " + pieces[1] + " " + pieces[2] + "\n"
                + pieces[3] + " " + pieces[4] + " " + pieces[5] + "\n"
                + pieces[6] + " " + pieces[7] + " " + pieces[8];
        return result;
    }

    /* Private Methods Below. End of Java Docs. */

    /* Checks if a given location is a valid location. */
    private boolean invalidLocation(int loc) {
        return (loc < 0 || loc > 8);
    }
}