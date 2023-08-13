package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game g;

    private final String[] icons = new String[]
            {"A","B","C","D","E","F","G","H"};

    @BeforeEach
    void runBefore() {
        g = new Game();
        g.setMaxDimension(4);
    }

    @Test
    void testConstructor() {
        assertTrue(g.getListofCard().isEmpty());
        assertTrue(g.getCoordinates().isEmpty());
        assertEquals(0, g.getScore().getScore());
        assertEquals(0, g.checkScore());
    }

    @Test
    void testFields() {
        g.createList(icons);
        assertEquals(4, g.getMaxDimension());
        assertFalse(g.checkFinished());
        assertEquals(16, g.getListofCard().size());
    }

    // EFFECTS: returns true only when EVERY card from the list are eliminated
    // otherwise return false
    @Test
    void testCheckFinished() {
        g.createList(icons);
        ArrayList<Card> loc = g.getListofCard();
        for (Card c: loc) {
            c.eliminated();
        }
        assertTrue(g.checkFinished());
    }

    @Test
    void testCheckNotFinished() {
        g.createList(icons);
        ArrayList<Card> loc = g.getListofCard();
        for (int i = 0; i < loc.size()-1; i++) {
            loc.get(i).eliminated();
        }
        assertFalse(g.checkFinished());
    }

    // MODIFIES: this, c1, c2
    // EFFECTS: checks if the two cards flipped are equal
    // the pair is eliminated if yes. Flipped back if not
    // a new plot is plotted afterward
    @Test
    void testCheckEqual() {
        MyImageIcon front = new MyImageIcon("./images/front.png");
        MyImageIcon a = new MyImageIcon("./images/A.png");
        MyImageIcon b = new MyImageIcon("./images/B.png");

        Card c1 = new Card();
        Card c2 = new Card();
        Card c3 = new Card();
        Card c4 = new Card();

        c1.setCard(1,"A");
        c2.setCard(1,"A");
        c3.setCard(2,"B");
        c4.setCard(3,"A"); // back shouldn't matter

        assertFalse(g.checkEqual(c1, c3));
        assertEquals(a, c1.getCard());
        assertEquals(b, c3.getCard());

        assertFalse(g.checkEqual(c1, c4));
        assertEquals(front, c1.getCard());
        assertEquals(a, c4.getCard());

        assertTrue(g.checkEqual(c1, c2));
        assertNull(c1.getCard());
        assertNull(c2.getCard());

        assertFalse(c1.getStatus());
        assertFalse(c2.getStatus());
        assertTrue(c3.getStatus());
        assertTrue(c4.getStatus());

    }


    // REQUIRES: s should not be "X"
    //           coordinatesX and coordinatesY must be numbers
    // EFFECTS: returns whether coordx and coordy are valid coordinates on the board
    @Test
    void testprocessCoordinates() {
        g.createList(icons);

        assertTrue(g.processCoordinates("0","0"));
        assertTrue(g.processCoordinates("3","3"));
        assertTrue(g.processCoordinates("3","0"));
        assertTrue(g.processCoordinates("0","3"));
        assertTrue(g.processCoordinates("1","1"));

        assertFalse(g.processCoordinates("-1","-1"));
        assertFalse(g.processCoordinates("0","-1"));
        assertFalse(g.processCoordinates("-1","0"));

        assertFalse(g.processCoordinates("4","4"));
        assertFalse(g.processCoordinates("0","4"));
        assertFalse(g.processCoordinates("4","0"));

        g.getListofCard().get(5).eliminated(); // x = 1, y = 1

        assertFalse(g.processCoordinates("1","1"));
    }

    @Test
    void testNewGame() {
        Card c1 = new Card();
        Card c2 = new Card();
        Card c3 = new Card();
        Card c4 = new Card();

        c1.setCard(1, "A");
        c2.setCard(1, "A");
        c3.setCard(2, "B");
        c4.setCard(3, "A");

        g.getListofCard().add(c1);
        g.getListofCard().add(c2);
        g.getListofCard().add(c3);
        g.getListofCard().add(c4);

        g.setMaxDimension(2);

        g.addCoordinates(0); // c1
        assertEquals(1, g.getCoordinates().size());
        assertEquals(c1, g.getLastCardChosen());

        assertFalse(g.processCoordinates("0","0")); // not same index
        assertTrue(g.processCoordinates("0","1"));
        assertTrue(g.processCoordinates("1","0"));
        assertTrue(g.processCoordinates("1","1"));

        g.resetCoordinates();
        assertEquals(0, g.getCoordinates().size());

        g.getListofCard().get(0).eliminated();
        g.getListofCard().get(1).eliminated();

        g.addCoordinates(2); // c1
        assertEquals(1, g.getCoordinates().size());
        assertEquals(c3, g.getLastCardChosen());

        assertFalse(g.processCoordinates("0","0"));
        assertFalse(g.processCoordinates("0","1"));
        assertFalse(g.processCoordinates("1","0"));
        assertTrue(g.processCoordinates("1","1"));

        // A A
        // B (A)        (A) is not like the others
    }

}