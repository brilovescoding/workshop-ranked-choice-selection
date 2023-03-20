import java.util.ArrayList;
import java.util.HashMap;

public class Attendee {
    private final String name;
    private final int grade;
    private final String emailAddress;
    private String[] workshopPreferences; //in order of preference

    //references to workshops - do not instantiate in this class, only assign
    private HashMap<WorkshopSessions, Workshop> workshops;
    public Attendee(String emailAddress, String name, int grade, String[] workshopPreferences) {
        this.name = name;
        this.grade = grade;
        this.emailAddress = emailAddress;
        this.workshopPreferences = workshopPreferences;
        this.workshops = new HashMap<WorkshopSessions, Workshop>(WorkshopSessions.values().length);
        //students get something in each Workshop slot
    }

    public String[] getWorkshopPreferences() {
        return workshopPreferences;
    }


    //gets Workshop assigned to student at that session
    public Workshop getWorkshop(WorkshopSessions sessionChar) {

        return null;
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
        if (getListOfAvailableSessions().size() > 0) {
            return true;
        }
        return false;
    }


}
