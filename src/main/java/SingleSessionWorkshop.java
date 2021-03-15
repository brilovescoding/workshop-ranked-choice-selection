import java.util.ArrayList;

public class SingleSessionWorkshop extends Workshop {

    private ArrayList<Attendee> attendees;
    private char session; //A or B


    public SingleSessionWorkshop(int id, String name, String description, String url, String moderators, String presenters, String type, char session) {
        super(id, name, description, url, moderators, presenters, type);
        this.session = session;
        attendees = new ArrayList<Attendee>();
    }

    public char getSession() {
        return session;
    }
    public void addAttendee(Attendee a) {
        if (a != null) {
            attendees.add(a);
        }
    }

    public String toString() {
        String s = super.toString();
        s += "Attendees: \n";
        for (Attendee a: attendees) {
            s += a.toString();
        }
        s += "\n";
        return s;
    }
}
