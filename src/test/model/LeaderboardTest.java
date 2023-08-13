package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardTest {
    private Leaderboard lb;

    ArrayList<Score> a;
    ArrayList<String> a1;
    ArrayList<String> a2;
    ArrayList<String> a3;

    @BeforeEach
    void runBefore() {
        lb = new Leaderboard();
        a = new ArrayList<>();
        a1 = new ArrayList<>();
        a2 = new ArrayList<>();
        a3 = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(a, lb.getAll());
        assertEquals(a1, lb.getBoardEasy());
        assertEquals(a2, lb.getBoardMedium());
        assertEquals(a3, lb.getBoardHard());
    }

    // MODIFIES: this
    // EFFECTS: adds the score to the board
    @Test
    void testAddScoreToBoard() {
        Score s0 =  new Score();
        s0.setName("Jacob");
        lb.addScoreToBoard(s0, "");
        assertEquals(a1, lb.getBoardEasy());
        assertEquals(0, lb.numScores());
        assertEquals(a, lb.getAll());

        Score s1 =  new Score();
        s1.setName("John");
        lb.addScoreToBoard(s1, "Easy");
        a1.add("John: 0 pts");
        a.add(s1);
        assertEquals(a1, lb.getBoardEasy());
        assertEquals(1, lb.numScores());
        assertEquals(a, lb.getAll());

        Score s2 =  new Score();
        s2.setName("George");
        s2.addPoints(50);
        lb.addScoreToBoard(s2, "Easy");
        a1.clear();
        a1.add("George: 50 pts");
        a1.add("John: 0 pts");
        a.clear();
        a.add(s2);
        a.add(s1);
        assertEquals(a1, lb.getBoardEasy());
        assertEquals(2, lb.numScores());
        assertEquals(a, lb.getAll());


        Score s3 =  new Score();
        s3.setName("Peppa");
        s3.addPoints(50);
        lb.addScoreToBoard(s3, "Medium");
        a2.add("Peppa: 50 pts");
        a.add(s3);
        assertEquals(a2, lb.getBoardMedium());
        assertEquals(3, lb.numScores());
        assertEquals(a, lb.getAll());

        Score s4 =  new Score();
        s4.setName("Jace");
        lb.addScoreToBoard(s4, "Medium");
        a2.add("Jace: 0 pts");
        a.add(s4);
        assertEquals(a2, lb.getBoardMedium());
        assertEquals(4, lb.numScores());
        assertEquals(a, lb.getAll());

        Score s5 =  new Score();
        s5.setName("Timmy");
        s5.addPoints(50);
        lb.addScoreToBoard(s5, "Hard");
        a3.add("Timmy: 50 pts");
        a.add(s5);
        assertEquals(a3, lb.getBoardHard());
        assertEquals(5, lb.numScores());
        assertEquals(a, lb.getAll());

        Score s6 =  new Score();
        s6.setName("Maria");
        lb.addScoreToBoard(s6, "Hard");
        a3.add("Maria: 0 pts");
        a.add(s6);
        assertEquals(a3, lb.getBoardHard());
        assertEquals(6, lb.numScores());
        assertEquals(a, lb.getAll());

        Score s7 =  new Score();
        s7.setName("George");
        s7.addPoints(50);
        lb.addScoreToBoard(s7, "Hard");
        a3.clear();
        a3.add("Timmy: 50 pts");
        a3.add("George: 50 pts");
        a3.add("Maria: 0 pts");
        a.clear();
        a.add(s2);
        a.add(s1);
        a.add(s3);
        a.add(s4);
        a.add(s5);
        a.add(s7);
        a.add(s6);
        assertEquals(a3, lb.getBoardHard());
        assertEquals(7, lb.numScores());
        assertEquals(a, lb.getAll());

        Score s8 =  new Score();
        s8.setName("Tommy");
        lb.addScoreToBoard(s8, "Hard");
        a3.add("Tommy: 0 pts");
        a.add(s8);
        assertEquals(a3, lb.getBoardHard());
        assertEquals(8, lb.numScores());
        assertEquals(a, lb.getAll());

    }
}
