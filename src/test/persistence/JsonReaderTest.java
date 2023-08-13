package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Leaderboard lb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLeaderboard() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeaderboard.json");
        try {
            Leaderboard lb = reader.read();
            assertEquals(0, lb.numScores());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLeaderboard() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLeaderboard.json");
        try {
            Leaderboard lb = reader.read();
            List<Score> scores = lb.getAll();

            assertEquals(4, scores.size());
            checkScore("default", 100, scores.get(0));
            checkScore("David", 20, scores.get(1));
            checkScore("default", 100, scores.get(2));
            checkScore("default", 100, scores.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}