package ui.tabs;

import ui.MatchingCardGameUI;
import ui.gameui.GameFrame;

import javax.swing.*;
import java.awt.*;

// Represents the main home tab and its elements and behaviors that involve the user interface
// The player can select which game difficulty to play against through this class.
// Source: from SmartHome -> ui/tabs/HomeTab
public class HomeTab extends Tab {

    JPanel buttonPanel = new JPanel(new GridBagLayout());

    private static final String INIT_GREETING = "Which game difficulty would you like?:";
    private JLabel greeting;

    //EFFECTS: constructs a home tab with buttons and a greeting
    public HomeTab(MatchingCardGameUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeHomeButtons();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 5);
        this.add(greeting);
    }

    //EFFECTS: creates 3 buttons that creates different game modes when clicked
    // First button - Easy Mode: creates a Game Frame with a game on easy mode
    // Second button - Medium Mode: creates a Game Frame with a game on medium mode
    // Third button - Hard Mode: creates a Game Frame with a game on hard mode
    //Source: https://stackoverflow.com/questions/29813566/how-do-i-create-spacing-in-between-jbuttons
    private void placeHomeButtons() {
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = .25;
        c.insets = new Insets(5, 70, 5, 70);// Top, Left, Bottom, Right spacing
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        JButton b1 = new JButton("Easy Mode");
        JButton b2 = new JButton("Medium Mode");
        JButton b3 = new JButton("Hard Mode");
        setButtonListeners(b1);
        setButtonListeners(b2);
        setButtonListeners(b3);

        buttonPanel.add(b1, c);
        buttonPanel.add(b2, c);
        buttonPanel.add(b3, c);

        this.add(buttonPanel);
    }

    // MODIFIES: b
    // EFFECTS: adds an action to the button given depending on its buttonString
    // When buttonString is Easy Mode: creates a Game Frame with a game on easy mode
    // When buttonString is Medium Mode: creates a Game Frame with a game on medium mode
    // When buttonString is Hard Mode: creates a Game Frame with a game on hard mode
    private void setButtonListeners(JButton b) {
        String buttonString = b.getText();
        if (buttonString.equals("Easy Mode")) {
            b.addActionListener(e -> {
                new GameFrame("Easy", getController());
            });
        } else if (buttonString.equals("Medium Mode")) {
            b.addActionListener(e -> {
                new GameFrame("Medium", getController());
            });
        } else if (buttonString.equals("Hard Mode")) {
            b.addActionListener(e -> {
                new GameFrame("Hard", getController());
            });
        }
    }
}
