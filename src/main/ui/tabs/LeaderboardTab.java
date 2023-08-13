package ui.tabs;

import model.Leaderboard;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.MatchingCardGameUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents the tab that has a leaderboard and its elements and behaviors that involve the user interface
// The player can view, save and load leaderboards through this class.
// Source: from SmartHome -> ui/tabs/HomeTab
public class LeaderboardTab extends Tab {
    private static final String JSON_STORE = "./data/leaderboard.json";

    private Leaderboard lb;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String INIT_GREETING = "Please select one of the following options:";
    private JLabel greeting;

    //EFFECTS: constructs a leaderboard tab with buttons and a greeting
    public LeaderboardTab(MatchingCardGameUI controller) {
        super(controller);

        lb = controller.getLeaderboard();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

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

    //EFFECTS: creates 3 buttons that does different things when clicked
    // First button - View Leaderboard: prompts the player to view a leaderboard
    // Second button - Save leaderboard to file: saves the current leaderboard to file
    // Third button - Load leaderboard from file: loads the file's leaderboard to the game's leaderboard
    // Source: https://stackoverflow.com/questions/29813566/how-do-i-create-spacing-in-between-jbuttons
    private void placeHomeButtons() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = .25;
        c.insets = new Insets(5, 70, 5, 70);// Top, Left, Bottom, Right spacing
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        JButton b1 = new JButton("View Leaderboard");
        JButton b2 = new JButton("Save leaderboard to file");
        JButton b3 = new JButton("Load leaderboard from file");
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
    // When buttonString is "View Leaderboard": prompts the player to view a leaderboard
    // When buttonString is "Save leaderboard to file": saves the current leaderboard to file
    // When buttonString is "Load leaderboard from file": loads the file's leaderboard to the game's leaderboard
    private void setButtonListeners(JButton b) {
        String buttonString = b.getText();
        if (buttonString.equals("View Leaderboard")) {
            b.addActionListener(e -> {
                updateLeaderboard();
                askBoard();
            });
        } else if (buttonString.equals("Save leaderboard to file")) {
            b.addActionListener(e -> {
                updateLeaderboard();
                saveLeaderboard();
            });
        } else if (buttonString.equals("Load leaderboard from file")) {
            b.addActionListener(e -> {
                loadLeaderboard();
            });
        }
    }

    // EFFECTS: ask the player from which leaderboard he wants to display (or None)
    // Source: https://stackoverflow.com/questions/9535748/showoptiondialog-buttons-on-separate-lines
    private void askBoard() {
        Object[] options = {
                "Easy Mode",
                "Medium Mode",
                "Hard Mode",
                "None (Quit)"
        };
        JComboBox optionList = new JComboBox(options);
        optionList.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, optionList, "From which difficulty?",
                JOptionPane.QUESTION_MESSAGE);

        int response = optionList.getSelectedIndex();

        if (response == 0) {
            viewLB("e");
        } else if (response == 1) {
            viewLB("m");
        } else if (response == 2) {
            viewLB("h");
        }
    }

    // REQUIRES: s to be either "e", "m", or "h"
    // EFFECTS: removes every element on the panel and creates a leaderboard according to the string given
    // Source: https://stackoverflow.com/questions/38349445/how-to-delete-all-components-in-a-jpanel-dynamically
    private void viewLB(String s) {
        this.removeAll();
        this.revalidate();
        this.repaint();
        if (s.equals("e")) {
            viewEasyLB();
        } else if (s.equals("m")) {
            viewMediumLB();
        } else if (s.equals("h")) {
            viewHardLB();
        }
    }

    // EFFECTS: creates the JPanel that displays every score with its player name from the easy difficulty leaderboard
    // The board has 1st, 2nd and 3rd place maximum
    private void viewEasyLB() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 10, 5));
        buttonPanel.setPreferredSize(new Dimension(400, 400));

        JLabel greeting = new JLabel("Easy Mode Leaderboard", JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 5);
        this.add(greeting);

        ArrayList<String> places = lb.getBoardEasy();

        if (places.size() < 3) {
            for (int i = 0; i < places.size(); i++) {
                JLabel place = new JLabel(places.get(i), JLabel.CENTER);
                place.setSize(WIDTH, HEIGHT / 5);
                buttonPanel.add(place);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                JLabel place = new JLabel(places.get(i), JLabel.CENTER);
                place.setSize(WIDTH, HEIGHT / 5);
                buttonPanel.add(place);
            }
        }

        this.add(buttonPanel);

        insertReturnButton();
    }

    // EFFECTS: creates the JPanel that displays every score with its player name from the medium difficulty leaderboard
    // The board has 1st, 2nd and 3rd place maximum
    private void viewMediumLB() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 10, 5));
        buttonPanel.setPreferredSize(new Dimension(400, 400));

        JLabel greeting = new JLabel("Medium Mode Leaderboard", JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 5);
        this.add(greeting);

        ArrayList<String> places = lb.getBoardMedium();

        if (places.size() < 3) {
            for (int i = 0; i < places.size(); i++) {
                JLabel place = new JLabel(places.get(i), JLabel.CENTER);
                place.setSize(WIDTH, HEIGHT / 5);
                buttonPanel.add(place);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                JLabel place = new JLabel(places.get(i), JLabel.CENTER);
                place.setSize(WIDTH, HEIGHT / 5);
                buttonPanel.add(place);
            }
        }

        this.add(buttonPanel);

        insertReturnButton();
    }

    // EFFECTS: creates the JPanel that displays every score with its player name from the hard difficulty leaderboard
    // The board has 1st, 2nd and 3rd place maximum
    private void viewHardLB() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 10, 5));
        buttonPanel.setPreferredSize(new Dimension(400, 400));

        JLabel greeting = new JLabel("Hard Mode Leaderboard", JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 5);
        this.add(greeting);

        ArrayList<String> places = lb.getBoardHard();

        if (places.size() < 3) {
            for (int i = 0; i < places.size(); i++) {
                JLabel place = new JLabel(places.get(i), JLabel.CENTER);
                place.setSize(WIDTH, HEIGHT / 5);
                buttonPanel.add(place);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                JLabel place = new JLabel(places.get(i), JLabel.CENTER);
                place.setSize(WIDTH, HEIGHT / 5);
                buttonPanel.add(place);
            }
        }

        this.add(buttonPanel);

        insertReturnButton();
    }


    // EFFECTS: adds a button that returns to the main leaderboard tab when clicked
    private void insertReturnButton() {
        JPanel panel = new JPanel();
        JButton returnButton = new JButton("Return");

        returnButton.addActionListener(e -> {
            returnButton.setVisible(false);
            this.removeAll();
            placeGreeting();
            placeHomeButtons();
        });

        panel.add(returnButton);
        this.add(panel);
    }

    // EFFECTS: saves the leaderboard to file
    // Source: from JsonSerializationDemo -> ui/WorkRoomApp
    public void saveLeaderboard() {
        try {
            jsonWriter.open();
            jsonWriter.write(lb);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads leaderboard from file
    // Source: from JsonSerializationDemo -> ui/WorkRoomApp
    public void loadLeaderboard() {
        try {
            lb = jsonReader.read();
            getController().updateLeaderboard(lb);
            JOptionPane.showMessageDialog(null, "Loaded from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates this leaderboard with the controller's leaderboard
    public void updateLeaderboard() {
        this.lb = getController().getLeaderboard();
    }

}
