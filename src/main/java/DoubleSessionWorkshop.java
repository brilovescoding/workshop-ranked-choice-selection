import java.util.ArrayList;

public class DoubleSessionWorkshop extends Workshop {
    private ArrayList<Attendee> attendeesA;
    private ArrayList<Attendee> attendeesB;

    public DoubleSessionWorkshop(int id, String name, String description, String url, String[] moderators, String[] presenters) {
        super(id, name, description, url, moderators, presenters);
        attendeesA = new ArrayList<Attendee>();
        attendeesB = new ArrayList<Attendee>();
    }
}
