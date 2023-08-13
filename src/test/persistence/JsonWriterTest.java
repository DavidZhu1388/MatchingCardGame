package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Leaderboard lb = new Leaderboard();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeaderboard() {
        try {
            Leaderboard lb = new Leaderboard();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeaderboard.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeaderboard.json");
            lb = reader.read();
            assertEquals(0, lb.numScores());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLeaderboard() {
        try {
            Leaderboard lb = new Leaderboard();
            Score s1 = new Score();
            Score s2 = new Score();
            s1.setName("Jack");
            s1.setScore(50);
            s2.setName("Mike");
            s2.setScore(100);
            lb.addScoreToBoard(s1, "Medium");
            lb.addScoreToBoard(s2, "Hard");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeaderboard.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLeaderboard.json");
            lb = reader.read();
            List<Score> scores = lb.getAll();
            assertEquals(2, scores.size());
            checkScore("Jack", 50, scores.get(0));
            checkScore("Mike", 100, scores.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}