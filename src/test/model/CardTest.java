package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card p;

    private MyImageIcon front = new MyImageIcon("./images/front.png");
    private MyImageIcon back1 = new MyImageIcon("./images/Q.png");
    private MyImageIcon back2 = new MyImageIcon("./images/P.png");

    @BeforeEach
    void runBefore() {
        p = new Card();
    }

    @Test
    void testConstructor() {
        assertEquals(front, p.getCard());
        assertTrue(p.getStatus());
    }

    // REQUIRES: s should not be "X"
    // MODIFIES: this
    // EFFECTS: sets the cardId back of the card

    @Test
    void testSetCard() {
        p.setCard(0, "Q");
        assertEquals(0, p.getCardId());
        p.flip();
        assertEquals(back1, p.getCard());
        p.flip();
        assertEquals(front, p.getCard());

        p.setCard(3, "P");
        assertEquals(3, p.getCardId());
        p.flip();
        assertEquals(back2, p.getCard());
    }

    // MODIFIES: this
    // EFFECTS: makes the card look like it is empty
    @Test
    void eliminated() {
        p.setCard(0, "Q");
        assertEquals(0, p.getCardId());
        p.flip();
        assertEquals(back1, p.getCard());
        p.flip();
        assertEquals(front, p.getCard());

        p.eliminated();
        assertFalse(p.getStatus());
        assertEquals(null, p.getCard());
        p.flip();
        assertEquals(null, p.getCard());
    }
}
