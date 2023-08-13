package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents the leaderboard of the scores gotten so far
// Covers the behaviors that allow the user to check for the scores on the board
public class Leaderboard {
    ArrayList<Score> boardEasy;
    ArrayList<Score> boardMedium;
    ArrayList<Score> boardHard;

    // EFFECTS: instantiates the class
    public Leaderboard() {
        boardEasy = new ArrayList<>();
        boardMedium = new ArrayList<>();
        boardHard = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the score to the specified board and sorts the boards
    public void addScoreToBoard(Score score, String dl) {
        if (dl.equals("Easy")) {
            boardEasy.add(score);
        } else if (dl.equals("Medium")) {
            boardMedium.add(score);
        } else if (dl.equals("Hard")) {
            boardHard.add(score);
        }

        doSelectionSort(boardEasy);
        doSelectionSort(boardMedium);
        doSelectionSort(boardHard);

        EventLog.getInstance().logEvent(new Event("Added score to the leaderboard -> "
                + score.getName() + ": " + score.getScore()));
    }

    // MODIFIES: this
    // EFFECTS: sorts the list of scores from the highest score to the lowest score
    // Note: when the scores are the same, the score that was scored first will be first
    // https://stackoverflow.com/questions/41955427/selection-sort-using-arraylist
    public void doSelectionSort(ArrayList<Score> arr) {
        for (int i = 0; i < arr.size(); i++) {
            // find position of smallest num between (i + 1)th element and last element
            int pos = i;
            for (int j = i; j < arr.size(); j++) {
                if (arr.get(j).getScore() > arr.get(pos).getScore()) {
                    pos = j;
                }
            }
            // Swap min (smallest num) to current position on array
            Score min = arr.get(pos);
            arr.set(pos, arr.get(i));
            arr.set(i, min);
        }
    }

    // EFFECTS: returns each score and the player name from the easy board
    public ArrayList<String> getBoardEasy() {
        ArrayList<String> listofScores = new ArrayList<>();
        for (Score s: boardEasy) {
            listofScores.add(s.getName() + ": " + s.getScore() + " pts");
        }
        return listofScores;
    }

    // EFFECTS: returns each score and the player name from the medium board
    public ArrayList<String> getBoardMedium() {
        ArrayList<String> listofScores = new ArrayList<>();
        for (Score s: boardMedium) {
            listofScores.add(s.getName() + ": " + s.getScore() + " pts");
        }
        return listofScores;
    }

    // EFFECTS: returns each score and the player name from the hard board
    public ArrayList<String> getBoardHard() {
        ArrayList<String> listofScores = new ArrayList<>();
        for (Score s: boardHard) {
            listofScores.add(s.getName() + ": " + s.getScore() + " pts");
        }
        return listofScores;
    }

    // EFFECTS: returns the leaderboard as JSON object
    // Source: from JsonSerializationDemo -> model/WorkRoom
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Easy", scoresToJson(boardEasy));
        json.put("Medium", scoresToJson(boardMedium));
        json.put("Hard", scoresToJson(boardHard));
        return json;
    }

    // EFFECTS: returns scores in a board as a JSON array
    // Source: from JsonSerializationDemo -> model/WorkRoom
    private JSONArray scoresToJson(ArrayList<Score> a) {
        JSONArray jsonArray = new JSONArray();

        for (Score s : a) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns the number of scores from all boards
    public int numScores() {
        return boardEasy.size() + boardMedium.size() + boardHard.size();
    }

    // EFFECTS: returns every score objects from every board
    public ArrayList<Score> getAll() {
        ArrayList<Score> all = new ArrayList<>();
        all.addAll(boardEasy);
        all.addAll(boardMedium);
        all.addAll(boardHard);
        return all;
    }
}
