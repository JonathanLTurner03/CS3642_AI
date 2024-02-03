package Assignments.A1.tests.Piece;

import static org.junit.Assert.*;
import Assignments.A1.models.Piece;
import org.junit.Test;

/**
 * Tests the moveTo() for Piece.java.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class TestMoveTo {

    @Test
    public void testNegativeMove() {
        Piece test = new Piece(3, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            test.moveTo(-1);
        });
    }

    @Test
    public void testZeroMove() {
        Piece test = new Piece(3, 5);
        test.moveTo(0);
        assertEquals(0, test.getLoc());
    }

    @Test
    public void testEightMove() {
        Piece test = new Piece(5, 5);
        test.moveTo(8);
        assertEquals(8, test.getLoc());
    }

    @Test
    public void testNineMove() {
        Piece test = new Piece(3, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            test.moveTo(9);
        });
    }

    @Test
    public void MoveToSameLocation() {
        Piece test = new Piece(3, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            test.moveTo(3);
        });
    }

    @Test
    public void ValidMove() {
        Piece test = new Piece(3, 5);
        test.moveTo(4);
        assertEquals(4, test.getLoc());
    }

}
