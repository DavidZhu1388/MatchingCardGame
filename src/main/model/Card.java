package model;

import javax.swing.*;
import java.awt.*;

// Represents a card that has a front, back and an Id,
// Covers the behaviors that allows the user to check for elements of the card and compare it
// Source of the cards: https://opengameart.org/content/playing-cards-vector-png
// source: https://www.javatpoint.com/java-image
public class Card {
    // cardId serves as identification when comparing two cards
    private int cardId;
    private MyImageIcon front = new MyImageIcon("./images/front.png");
    private MyImageIcon back;

    private boolean isFlipped;
    // status serves to know whether the card is eliminated(false) or not(true)
    private boolean status;

    // EFFECTS: instantiates the class
    public Card() {
        isFlipped = false;
        status = true;
        setScaledImage(front);
    }

    // REQUIRES: s should not be "front"
    // MODIFIES: this
    // EFFECTS: sets the cardId back of the card
    public void setCard(int i, String s) {
        cardId = i;
        back = new MyImageIcon("./images/" + s + ".png");
        setScaledImage(back);
    }

    // MODIFIES: this
    // EFFECTS: eliminates the card
    // and makes the card look like it is empty on both sides
    public void eliminated() {
        front = null;
        back = null;
        status = false;
    }

    // MODIFIES: this
    // EFFECTS: flips the card
    public void flip() {
        isFlipped = !isFlipped;
    }

    // REQUIRES: front and back are initiated
    // EFFECTS: gets the side of the card that is facing the player
    public MyImageIcon getCard() {
        if (isFlipped) {
            return back;
        } else {
            return front;
        }
    }

    public int getCardId() {
        return cardId;
    }

    public boolean getStatus() {
        return status;
    }

    // EFFECTS: resizes the image given to a more preferred size
    // Source: https://www.tabnine.com/code/java/methods/java.awt.Image/getScaledInstance
    public void setScaledImage(ImageIcon i) {
        // Get the button size (assuming it's square)
        Image originalImage = i.getImage();
        Image scaledImage = originalImage.getScaledInstance(80, 117, Image.SCALE_SMOOTH);
        i.setImage(scaledImage);
    }

}
