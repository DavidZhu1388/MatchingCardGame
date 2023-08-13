package model;

import java.util.ArrayList;
import java.util.Collections;

// Covers the behaviors of the game that allows the user to check and adjust the elements of the game
public class Game {
    private ArrayList<Card> listofCard;

    private int maxDimension;

    private ArrayList<Integer> coordinates;

    private final Score score;

    // EFFECTS: instantiates the class
    public Game() {
        listofCard = new ArrayList<>();
        coordinates = new ArrayList<>();
        score = new Score();
    }

    // EFFECTS: returns true only when EVERY card from the list are eliminated
    // otherwise return false
    public boolean checkFinished() {
        for (int i = 0; i < listofCard.size(); i++) {
            if (listofCard.get(i).getStatus()) {
                return false;
            }
        }
        return true;
    }

    // MODIFIES: this, c1, c2
    // EFFECTS: checks if the two cards flipped are equal
    // the pair is eliminated if yes. Flipped back if not
    public boolean checkEqual(Card c1, Card c2) {
        if (c1.getCardId() == c2.getCardId()) {
            c1.eliminated();
            c2.eliminated();
            return true;
        } else {
            c1.flip();
            c2.flip();
            return false;
        }
    }

    // REQUIRES: size of the icons list == (maxDimension^2)/2
    // MODIFIES: this
    // EFFECTS: creates a list of shuffled pairs of cards.
    // The list has the length = icons*2
    public void createList(String[] icons) {
        int index = 0;
        for (String icon: icons) {
            Card c1 = new Card();
            c1.setCard(index, icon);
            Card c2 = new Card();
            c2.setCard(index, icon);
            listofCard.add(c1);
            listofCard.add(c2);
            index++;
        }

        Collections.shuffle(listofCard);
    }

    // REQUIRES: coordinatesX and coordinatesY must be numbers
    // EFFECTS: returns whether coordx AND coordy are valid coordinates on the board
    // valid coordinates are associated with cards not yet eliminated
    // and are not the same as the first card chosen(if any)
    public boolean processCoordinates(String coordx, String coordy) {
        int x = Integer.parseInt(coordx);
        int y = Integer.parseInt(coordy);
        int index = (y * maxDimension + x);
        boolean inRange = (0 <= x && x < maxDimension && 0 <= y && y < maxDimension);
        if (!inRange) {
            return false;
        }
        boolean notEliminated = listofCard.get(index).getStatus();
        if (coordinates.size() > 0) {
            boolean notSameIndex = coordinates.get(coordinates.size() - 1) != index;
            return (notEliminated && notSameIndex);
        } else {
            return (notEliminated);
        }
    }

    public ArrayList<Card> getListofCard() {
        return listofCard;
    }

    // MODIFIES: this
    // EFFECTS: adds the index of the card to the coordinates list
    public void addCoordinates(int i) {
        coordinates.add(i);
    }

    // MODIFIES: this
    // EFFECTS: clears the coordinates list
    public void resetCoordinates() {
        coordinates.clear();
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public int checkScore() {
        return score.getScore();
    }

    public Score getScore() {
        return score;
    }

    public void setMaxDimension(int i) {
        maxDimension = i;
    }

    public int getMaxDimension() {
        return maxDimension;
    }

    // EFFECTS: gets the last card from the coordinates list
    public Card getLastCardChosen() {
        return listofCard.get(coordinates.get(coordinates.size() - 1));
    }

}
