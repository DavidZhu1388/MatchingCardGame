package model;

import java.util.Calendar;
import java.util.Date;

// Represents a game event.
// Source: AlarmSystem -> main/model/Event
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;


    // EFFECTS: Creates an event with the given description and the current date/time stamp.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: Gets the date of this event (includes time).
    public Date getDate() {
        return dateLogged;
    }

    public String getDescription() {
        return description;
    }

    // EFFECTS: compares the creation date/time of both Events and their respective descriptions
    // If they are from the same source(i.e. have the same source string),
    // They will be considered as equal.
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
