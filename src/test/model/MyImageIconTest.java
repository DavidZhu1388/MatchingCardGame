package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class MyImageIconTest {

    MyImageIcon i1;
    MyImageIcon i2;
    MyImageIcon i3;
    ImageIcon i4;

    @BeforeEach
    void runBefore() {
        i1 = new MyImageIcon("./images/P.png");
        i2 = new MyImageIcon("./images/P.png");
        i3 = new MyImageIcon("./images/Q.png");
        i4 = new ImageIcon("./images/Q.png");
    }

    @Test
    void testConstructor() {
        assertEquals("./images/P.png", i1.getSource());
        assertEquals("./images/P.png", i2.getSource());
        assertEquals("./images/Q.png", i3.getSource());
    }

    @Test
    void testEquals() {
        assertTrue(i1.equals(i2));
        assertTrue(i1.equals(i1));
        assertTrue(i2.equals(i1));
        assertTrue(i2.equals(i2));
        assertFalse(i1.equals(i3));
        assertFalse(i3.equals(i1));
        assertFalse(i3.equals(null));
        assertFalse(i3.equals(i4));
    }
}
