package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    private Score s;

    @BeforeEach
    void runBefore() {
        s = new Score();
    }

    @Test
    void testConstructor() {
        assertEquals(0, s.getScore());
        assertEquals("", s.getName());
    }

    @Test
    void testAlgebra () {
        s.addPoints(1);
        assertEquals(1, s.getScore());

        s.addPoints(9);
        s.addPoints(40);
        assertEquals(50, s.getScore());

        s.minusPoints(1);
        assertEquals(49, s.getScore());

        s.minusPoints(9);
        s.minusPoints(40);
        assertEquals(0, s.getScore());
    }

    @Test
    void testName () {
        s.setName("George");
        assertEquals("George", s.getName());

        s.setName("Hannah");
        s.setName("Josh");
        assertEquals("Josh", s.getName());
    }

    @Test
    void testScore () {
        s.setScore(50);
        assertEquals(50, s.getScore());

        s.setScore(100);
        s.setScore(0);
        assertEquals(0, s.getScore());
    }
}
