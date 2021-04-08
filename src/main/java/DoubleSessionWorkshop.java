import java.util.ArrayList;

public class DoubleSessionWorkshop extends Workshop {
    private ArrayList<Attendee> attendeesA;
    private ArrayList<Attendee> attendeesB;

    public DoubleSessionWorkshop(int id, String name, String description, String url, String moderators, String presenters, String type) {
        super(id, name, description, url, moderators, presenters, type);
        attendeesA = new ArrayList<Attendee>();
        attendeesB = new ArrayList<Attendee>();
    }

    public void addAttendee(Attendee a, char session) {
        if (a != null) {
            if (session == 'A') {
                attendeesA.add(a);
            } else if (session == 'B') {
                attendeesB.add(a);
            }
        }
    }

    public ArrayList<Attendee> getAttendees(char session) {
        if (session == 'A') {
            return attendeesA;
        }
        return attendeesB;
    }

    public String toString() {
        String s = super.toString();
        s += "Attendees: \n";
        s += "Session A: \n";
        if (getNumberOfAttendees('A') > 0) {
            for (Attendee a : attendeesA) {
                s += a.toString();
                s += "\n";
            }
        }
        else {
            s += "None\n";
        }
        s += "Session B: \n";
        if (getNumberOfAttendees('B') > 0) {
            for (Attendee a: attendeesB) {
                s += a.toString();
                s += "\n";
            }
        }
        else {
            s += "None\n";
        }
        s += "\n";
        return s;
    }

    public int getNumberOfAttendees(char session) {
        if (session == 'A') { return attendeesA.size(); }
        else if (session == 'B') { return attendeesB.size(); }

        return -1;
    }
}
