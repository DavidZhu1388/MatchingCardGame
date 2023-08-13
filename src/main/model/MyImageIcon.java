package model;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// Represents an ImageIcon class extended from ImageIcon that allows to compare the source of the images
// source/inspiration: https://stackoverflow.com/questions/8309600/compare-two-image-icons
public class MyImageIcon extends ImageIcon {
    String source;

    // EFFECTS: instantiates the class
    public MyImageIcon(String source) {
        super(source);
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    // EFFECTS: compares the sources of the images from both ImageIcons.
    // If they are from the same source(i.e. have the same source string),
    // They will be considered as equal.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyImageIcon that = (MyImageIcon) o;

        return Objects.equals(source, that.source);
    }


}
