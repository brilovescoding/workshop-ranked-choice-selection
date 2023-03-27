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

    private HashMap<WorkshopSessions, ArrayList<Attendee>> sessionAttendees; //Hashmap

    public Workshop(String name, String description, String location, String moderators, String presenters, int maxAttendance, boolean isFreeTalk, HashMap<WorkshopSessions, ArrayList<Attendee>> attendees) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.moderators = moderators;
        this.presenters = presenters;
        this.maxAttendance = maxAttendance;
        this.isFreeTalk = isFreeTalk;
        this.sessionAttendees = attendees;
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
        return sessionAttendees;
    }

    public int getNumberOfOpenSpots(WorkshopSessions session) {
        return getMaxAttendance() - this.sessionAttendees.get(session).size();
    }

    //returns arraylist of keys for available workshop sessions
    public ArrayList<WorkshopSessions> getListOfAvailableSessions() {
        ArrayList<WorkshopSessions> temp = new ArrayList<WorkshopSessions>();
        //for each session (loop through hashmap):
        for (ArrayList<Attendee> a: sessionAttendees.values()) {
            Workshop w = workshops.get(sessionChar);
            if (w != null && w.getAttendees().size() < w.getMaxAttendance()) {
                temp.put(sessionChar, w);
            }
        }
        //if it's not null AND the attendance is not max, add it to the ArrayList, then return
        if (temp.isEmpty()) {
            return null;
        }

        return temp;

    }
}
