import java.util.ArrayList;

public class DoubleSessionWorkshop extends Workshop {
    private ArrayList<Attendee> attendeesA;
    private ArrayList<Attendee> attendeesB;

    public DoubleSessionWorkshop(String name, String description, String url, String moderators, String presenters, int maxAttendance, boolean isFreeTalk) {
        super(name, description, url, moderators, presenters, maxAttendance, isFreeTalk);
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
        try {
            if (this.getMaxAttendance() < this.getNumberOfAttendees('A')) {
                throw new Exception("Attendance cap overridden for " + this.getName() + "in Workshop A");
            }
            else if (this.getMaxAttendance() < this.getNumberOfAttendees('B')) {
                throw new Exception("Attendance cap overridden for " + this.getName() + "in Workshop B");
            }
        }
        catch (Exception e) {
            System.out.println(e);
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
