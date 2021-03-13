import java.util.ArrayList;

public class SingleSessionWorkshop extends Workshop {
    private ArrayList<Attendee> attendees;
    private char session; //A or B

    public SingleSessionWorkshop(int id, String name, String description, String url, String[] moderators, String[] presenters, char session) {
        super(id, name, description, url, moderators, presenters);
        this.session = session;
        attendees = new ArrayList<Attendee>();
    }

}
