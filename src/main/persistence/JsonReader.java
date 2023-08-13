package persistence;

import model.Leaderboard;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Score;
import org.json.*;

// Represents a reader that reads leaderboard from JSON data stored in file
// Source: from JsonSerializationDemo -> persistence/JsonReader
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads leaderboard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Leaderboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeaderboard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses leaderboard from JSON object and returns it
    private Leaderboard parseLeaderboard(JSONObject jsonObject) {
        Leaderboard lb = new Leaderboard();
        addScores(lb, jsonObject, "Easy");

        addScores(lb, jsonObject, "Medium");

        addScores(lb, jsonObject, "Hard");

        return lb;
    }

    // MODIFIES: lb
    // EFFECTS: parses scores from JSON object and adds them to leaderboard
    private void addScores(Leaderboard lb, JSONObject jsonObject, String dl) {
        JSONArray jsonArray = jsonObject.getJSONArray(dl);
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(lb, nextScore, dl);
        }
    }

    // MODIFIES: lb
    // EFFECTS: parses score from JSON object and adds it to leaderboard
    private void addScore(Leaderboard lb, JSONObject jsonObject, String dl) {
        String name = jsonObject.getString("Name");
        int scoreValue = jsonObject.getInt("Score");
        Score score = new Score();
        score.setName(name);
        score.setScore(scoreValue);
        lb.addScoreToBoard(score, dl);
    }
}
