package Assignments.A1.tests.Piece;

import static org.junit.Assert.*;
import Assignments.A1.models.Piece;
import org.junit.Test;

/**
 * Tests the constructor for Piece.java.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class TestConstructor {

    @Test
    public void testZeroValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(5, 0);
        });
    }

    @Test
    public void testOneValue() {
        Piece test = new Piece(5, 1);
        assertEquals(1, test.getValue());
    }

    @Test
    public void testEightValue() {
        Piece test = new Piece(5, 8);
        assertEquals(8, test.getValue());
    }

    @Test
    public void testNineValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(5, 9);
        });
    }

    @Test
    public void testNegativeLoc() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(-1, 5);
        });
    }

    @Test
    public void testZeroLoc() {
        Piece test = new Piece(0, 5);
        assertEquals(0, test.getLoc());
    }

    @Test
    public void testEightLoc() {
        Piece test = new Piece(8, 5);
        assertEquals(8, test.getLoc());
    }

    @Test
    public void testNineLoc() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(9, 5);
        });
    }


    @Test
    public void validParams() {
        Piece test = new Piece(3, 6);
        assertEquals(3, test.getLoc());
        assertEquals(6, test.getValue());
    }

}
