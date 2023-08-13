package ui.tabs;

import ui.MatchingCardGameUI;

import javax.swing.*;
import java.awt.*;

// Represents a generic tab that has the controller that controls it
// Source: from SmartHome -> ui/tabs/HomeTab
public abstract class Tab extends JPanel {

    private final MatchingCardGameUI controller;

    //REQUIRES: MatchingCardGameUI controller that holds this tab
    //EFFECTS: constructs the Tab
    public Tab(MatchingCardGameUI controller) {
        this.controller = controller;
    }

    //EFFECTS: returns the MatchingCardGameUI controller for this tab
    public MatchingCardGameUI getController() {
        return controller;
    }

}
