package ui.gameui;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Represents a card that has a front, back, an id and the GameFrame that is controlling it
// Covers the behaviors that allows the user to check for elements of the card
public class CardUI extends JPanel {
    private Card card;

    private GameFrame controller;

    private int index;

    JButton imageButton;

    // EFFECTS: instantiates the class and creates a button that has the card's image and flips when clicked on
    public CardUI(Card card, GameFrame controller, int index) {
        setLayout(new BorderLayout());
        this.card = card;
        this.controller = controller;
        this.index = index;
        imageButton = new JButton(card.getCard());
        add(imageButton, BorderLayout.CENTER);

        // Add a mouse click listener to the button
        imageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.flip();
                setCard();
                addtoController();
            }
        });

    }

    // MODIFIES: this
    // EFFECTS: adds the cardUI instance to the controller for it to keep track of what has been clicked on
    private void addtoController() {
        controller.addRecentCardChosen(this, index);
    }

    // EFFECTS: makes the button invisible
    public void disable() {
        setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: sets the icon of the button with the Card's current face(front or back) image
    public void setCard() {
        imageButton.setIcon(card.getCard());
    }

    // MODIFIES: this
    // EFFECTS: flips the card and sets the icon of the button with the Card's current face(front or back) image
    public void flipBack() {
        card.flip();
        imageButton.setIcon(card.getCard());
    }

}