package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkScore(String name, int scoreInt, Score score) {
        assertEquals(name, score.getName());
        assertEquals(scoreInt, score.getScore());
    }
}
