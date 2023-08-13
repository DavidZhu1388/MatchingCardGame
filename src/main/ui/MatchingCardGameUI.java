package ui;

import model.Event;
import model.EventLog;
import model.Leaderboard;
import ui.tabs.HomeTab;
import ui.tabs.LeaderboardTab;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represents the entire application menus' user interface
// Covers the menu selection and displaying the leaderboard elements rather than the game itself
// Source: from SmartHome -> ui/SmartHomeUI
public class MatchingCardGameUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int SETTINGS_TAB_INDEX = 1;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JTabbedPane sidebar;

    private Leaderboard lb = new Leaderboard();

    //EFFECTS: creates the UI, loads the game selection and leaderboard tabs and displays sidebar
    //Source when exit: https://stackoverflow.com/questions/12210972/setdefaultcloseoperation-to-show-a-jframe-instead
    public MatchingCardGameUI() {
        super("Matching Card Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString() + "\n\n");
                }
                System.exit(0);
            }
        });

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.BOTTOM);

        loadTabs();
        add(sidebar);

        setVisible(true);
        setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: adds home tab and leaderboard tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel lbTab = new LeaderboardTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(lbTab, SETTINGS_TAB_INDEX);
        sidebar.setTitleAt(SETTINGS_TAB_INDEX, "Leaderboard");
    }

    public Leaderboard getLeaderboard() {
        return lb;
    }

    // REQUIRES: Only the loadLeaderboard() method from LeaderboardTab should access this method
    // MODIFIES: this
    // EFFECTS: updates the current leaderboard with the one given.
    public void updateLeaderboard(Leaderboard lb) {
        this.lb = lb;
    }

}
