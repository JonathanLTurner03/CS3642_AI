package Assignments.A1.models;

/**
 * Used to pair two points for moves.
 * @author Jonathan Turner
 * @version Spring 2024
 * @param <E> the type of the pair.
 */
public class Pair<E> {

    /* the first and second points */
    public E first;
    public E second;

    /**
     * Creates and sets the first and second point of the pair.
     *
     * @precondition none
     * @postcondition a pair is created.
     *
     * @param first the first point of the pair
     * @param second the second point of the pair
     */
    public Pair(E first, E second) {
        this.first = first;
        this.second = second;
    }

}
