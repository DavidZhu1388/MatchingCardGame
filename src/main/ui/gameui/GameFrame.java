package ui.gameui;

import model.Card;
import model.Game;
import model.Score;
import ui.MatchingCardGameUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

// Represents the game elements and behaviors that involve the user interface
public class GameFrame extends JFrame {

    private final String[] iconsE = new String[]
            {"A", "B"};

    private final String[] iconsM = new String[]
            {"A", "B", "C", "D", "E", "F", "G", "H"};

    private final String[] iconsH = new String[]
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R"};

    private MatchingCardGameUI controller;

    private Game game = new Game();

    private String dl;

    private String scoreString = "Score: ";

    private JLabel scoreLabel = new JLabel();

    private ArrayList<CardUI> cardUIArrayList = new ArrayList<>();

    //EFFECTS: constructs a game frame and plots the game elements depending on the string given
    // Easy: 2x2 grid with 2 pairs of cards
    // Medium: 4x4 grid with 8 pairs of cards
    // Hard: 6x6 grid with 18 pairs of cards
    // Source for the scorePanel: http://www.java2s.com/Tutorials/Java/Swing_How_to/JFrame/Add_a_status_bar_to_the_bottom_of_JFrame.htm
    public GameFrame(String s, MatchingCardGameUI controller) {
        this.controller = controller;

        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(4, 4, 4, 4)));
        scoreLabel.setText(scoreString + game.checkScore());
        statusBar.add(scoreLabel);
        add(statusBar, BorderLayout.NORTH);

        if (s.equals("Easy")) {
            dl = s;
            startEasyGame();
        } else if (s.equals("Medium")) {
            dl = s;
            startMediumGame();
        } else if (s.equals("Hard")) {
            dl = s;
            startHardGame();
        }

        // Show the JFrame
        setVisible(true);
        setResizable(false);
    }


    // MODIFIES: this
    // EFFECTS: plots the easy mode game elements onto the frame
    // It has a 2x2 grid with 2 pairs of cards
    private void startEasyGame() {
        setTitle("Easy Mode");
        setSize(217, 330);

        game.createList(iconsE);
        game.setMaxDimension(2);

        ArrayList<Card> listofCard = game.getListofCard();

        // Create the JPanel for the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 5));

        // Add cells to the main panel
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int index = i * 2 + j;
                CardUI cellPanel = new CardUI(listofCard.get(index), this, index);
                buttonPanel.add(cellPanel);
            }
        }

        add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: plots the medium mode game elements onto the frame
    // It has a 4x4 grid with 8 pairs of cards
    private void startMediumGame() {
        setTitle("Medium Mode");
        setSize(433, 600);

        game.createList(iconsM);
        game.setMaxDimension(4);

        ArrayList<Card> listofCard = game.getListofCard();

        // Create the JPanel for the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 5));

        // Add cells to the main panel
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int index = i * 4 + j;
                CardUI cellPanel = new CardUI(listofCard.get(index), this, index);
                buttonPanel.add(cellPanel);
            }
        }

        this.add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: plots the hard mode game elements onto the frame
    // It has a 6x6 grid with 18 pairs of cards
    private void startHardGame() {
        setTitle("Hard Mode");
        setSize(650, 900);

        game.createList(iconsH);
        game.setMaxDimension(6);

        ArrayList<Card> listofCard = game.getListofCard();

        // Create the JPanel for the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 6, 10, 5));

        // Add cells to the main panel
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                CardUI cellPanel = new CardUI(listofCard.get(index), this, index);
                buttonPanel.add(cellPanel);
            }
        }

        this.add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds a unique cardUI to the lists for keeping track
    public void addRecentCardChosen(CardUI cardui, int index) {
        if (cardUIArrayList.size() == 1) {
            // prevent player from clicking the same card twice
            if (cardUIArrayList.get(0) != cardui) {
                game.addCoordinates(index);
                cardUIArrayList.add(cardui);

                if (game.getCoordinates().size() == 2) {
                    compareTwoDifferentCardUIs();
                }
                updateCurrentScore();

                checkFinished();
            } else {
                cardui.flipBack();
            }
        } else {
            game.addCoordinates(index);
            cardUIArrayList.add(cardui);
        }

    }

    // MODIFIES: this
    // EFFECTS: compares the two different CardUI that the player has clicked on
    // the two cards are eliminated and disabled if yes, flipped back if not
    // both lists that keeps track of the CardUI are reset afterward
    private void compareTwoDifferentCardUIs() {
        ArrayList<Card> loc = game.getListofCard();
        ArrayList<Integer> loi = game.getCoordinates();
        if (game.checkEqual(loc.get(loi.get(0)), loc.get(loi.get(1)))) {
            JOptionPane.showMessageDialog(null, "Yay, you got a pair!");
            game.getScore().addPoints(10);
            for (CardUI c : cardUIArrayList) {
                c.disable();
            }
            game.resetCoordinates();
            cardUIArrayList.clear();
        } else {
            JOptionPane.showMessageDialog(null, "wrong pair!");
            game.getScore().minusPoints(5);
            for (CardUI c : cardUIArrayList) {
                c.setCard();
            }
            game.resetCoordinates();
            cardUIArrayList.clear();
        }
    }

    // EFFECTS: checks if the game is finished.
    // If yes, the game asks the player if he wants to record(not save) the score to the leaderboard
    // the frame is disposed afterward.
    private void checkFinished() {
        if (game.checkFinished()) {
            JOptionPane.showMessageDialog(null,
                    "Congratulations! Your final score is " + game.checkScore());
            uploadScore(game.getScore(), dl);
            dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: asks the player if he wants to record(not save) the score to the leaderboard
    // saves if he clicks on yes and submits a name, doesn't save otherwise
    // Source1(for the input window): https://mkyong.com/swing/java-swing-joptionpane-showinputdialog-example/
    // Source2(for the YesNo window): https://stackoverflow.com/questions/8396870/joptionpane-yes-or-no-window
    private void uploadScore(Score s, String dl) {
        int reply = JOptionPane.showConfirmDialog(null,
                "Do you want to record the score to the leaderboard?", null, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String response = JOptionPane.showInputDialog("Input your name");
            if (response != null) {
                s.setName(response);
                controller.getLeaderboard().addScoreToBoard(s, dl);
                JOptionPane.showMessageDialog(null, "Score recorded! "
                        + "(Ps: for persisted storage, please check the main menu)");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Score not recorded!");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the current score of the game to the player
    private void updateCurrentScore() {
        scoreLabel.setText(scoreString + game.checkScore());
    }
}
