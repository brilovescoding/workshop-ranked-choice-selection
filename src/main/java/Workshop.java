import java.util.ArrayList;
import java.util.HashMap;


public class Workshop {
    private final String name;
    private final String description;
    private final String location;
    private final String moderators;
    private final String presenters;
    private final int maxAttendance;
    private final boolean isFreeTalk;

    private HashMap<WorkshopSessions, ArrayList<Attendee>> attendees; //Hashmap

    public Workshop(String name, String description, String location, String moderators, String presenters, int maxAttendance, boolean isFreeTalk) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.moderators = moderators;
        this.presenters = presenters;
        this.maxAttendance = maxAttendance;
        this.isFreeTalk = isFreeTalk;
        this.attendees = new HashMap<WorkshopSessions, ArrayList<Attendee>>();
    }
    public String toString() {
        return "Name: " + name + "\nDescription: " + description + "\nLocation: " + location + "\nPresenters: " + presenters + "\nModerators: " + moderators + "\n";
    }

    public int getMaxAttendance() {
        return maxAttendance;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getModerators() {
        return moderators;
    }

    public String getPresenters() {
        return presenters;
    }

    public boolean isFreeTalk() {
        return isFreeTalk;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<WorkshopSessions, ArrayList<Attendee>> getAttendees() {
        return attendees;
    }
}
