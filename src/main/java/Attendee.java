import java.util.ArrayList;
import java.util.HashMap;

public class Attendee {
    private final String name;
    private final int grade;
    private final String emailAddress;
    private String[] workshopPreferences; //in order of preference

    private HashMap<WorkshopSessions, Workshop> workshops;
    public Attendee(String emailAddress, String name, int grade, String[] workshopPreferences) {
        this.name = name;
        this.grade = grade;
        this.emailAddress = emailAddress;
        this.workshopPreferences = workshopPreferences;
        this.workshops = new HashMap<WorkshopSessions, Workshop>(WorkshopSessions.values().length);

        //students get something in each Workshop slot. The length of the WorkshopSessions enum determines the maximum size of the Hashmap
    }

    public String[] getWorkshopPreferences() {
        return workshopPreferences;
    }


    //gets Workshop assigned to student at that session
    public Workshop getWorkshop(WorkshopSessions sessionChar) {
        return workshops.get(sessionChar);
    }

    public void setWorkshop(Workshop w, WorkshopSessions ws) {
        workshops.put(ws, w);
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean isAvailable() {
        return getListOfAvailableSessions().size() > 0 ? true:false;
    }

    //returns true if student does not have a value associated with that key
    public boolean isAvailable(WorkshopSessions sessionChar) {
        return getListOfAvailableSessions().get(sessionChar) == null ? true: false;
    }

    public boolean isStudentAlreadyInWorkshop(Workshop w) {
        //loop through workshops
        //if the workshop's reference matches another's, return true
        for (Workshop workshop: workshops.values()) {
            if (workshop == w) {
                return true;
            }
        }
        return false;
    }

    //returns available sessions for a given attendee
    public HashMap<WorkshopSessions, Workshop> getListOfAvailableSessions() {
        HashMap<WorkshopSessions, Workshop> temp = new HashMap<WorkshopSessions, Workshop>();
        //for each workshop session type (loop through enum):
        for (WorkshopSessions sessionChar: WorkshopSessions.values()) {
            Workshop w = workshops.get(sessionChar);
            if (w != null && w.getAttendees(sessionChar).size() < w.getMaxAttendance()) {
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
