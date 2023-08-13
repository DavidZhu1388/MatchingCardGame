package model;

import org.json.JSONObject;

// Represents the score element linked to a game
// Covers the behaviors that allows the user to check and adjust the elements of the score
public class Score {
    private int score;
    private String name;

    // EFFECTS: instantiates the class
    public Score() {
        score = 0;
        name = "";
    }

    // REQUIRES: points > 0
    // MODIFIES: this
    // EFFECTS: adds points to score
    public void addPoints(int points) {
        score += points;
        String description = "Added " + points + " pts to the current score";
        EventLog.getInstance().logEvent(new Event(description));
    }

    // REQUIRES: points > 0 && score >= points
    // MODIFIES: this
    // EFFECTS: subtracts points to score
    public void minusPoints(int points) {
        score -= points;
        String description = "Subtracted " + points + " pts to the current score";
        EventLog.getInstance().logEvent(new Event(description));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int i) {
        score = i;
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns this as JSON object
    // Source: from JsonSerializationDemo -> model/Thingy
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Score", score);
        return json;
    }
}
