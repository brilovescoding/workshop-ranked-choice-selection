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

    public ArrayList<Attendee> getAttendees(WorkshopSessions session) {
            return sessionAttendees.get(session);
    }

    public void addAttendee(WorkshopSessions session, Attendee a) {
        sessionAttendees.get(session).add(a);
    }

    public int getNumberOfAttendees(WorkshopSessions session) {
        return this.sessionAttendees.get(session).size();
    }

    public int getNumberOfOpenSpots(WorkshopSessions session) {
        return getMaxAttendance() - getNumberOfAttendees(session);
    }

    //returns arraylist of keys for available workshop sessions
    //I guess I am using it when placing a student in that workshop, to see if there is an available session for them
    //I also want to ensure that they are not already scheduled for a different session
    //I need the key because I need to loop through each letter of a workshop to determine
    //that each student only gets one of each

    public ArrayList<WorkshopSessions> getListOfAvailableSessions() {
        ArrayList<WorkshopSessions> temp = new ArrayList<WorkshopSessions>();
        //for each session (loop through each arrayList of attendees in the Hashmap):

        sessionAttendees.forEach( (sessionKey, sessionArrayList) -> {
            if (sessionArrayList.size() < this.getMaxAttendance()) {
                temp.add(sessionKey);
            }
        });

        //if it's not null AND the attendance is not max, add it to the ArrayList, then return
        if (temp.isEmpty()) {
            return new ArrayList<>();
        }
        return temp;
    }
}
