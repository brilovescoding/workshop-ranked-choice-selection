/*
An AttendeeList contains an ArrayList of Attendees but also a character indicating which workshop session
it is assigned to.

 */

import java.util.ArrayList;

public class AttendeeList {
    private ArrayList<Attendee> attendees;
    private char session;

    public AttendeeList(char session) {
        this.attendees = new ArrayList<Attendee>();
        this.session = session;
    }
}
