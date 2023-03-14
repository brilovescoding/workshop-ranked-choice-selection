import java.util.ArrayList;

public class Attendee {
    private String name;
    private int grade;
    private String emailAddress;
    private String[] workshopPreferences; //in order of preference

    //references to workshops - do not instantiate in this class, only assign
    private Workshop[] workshops;

    public Attendee(String emailAddress, String name, int grade, String[] workshopPreferences) {
        this.name = name;
        this.grade = grade;
        this.emailAddress = emailAddress;
        this.workshopPreferences = workshopPreferences;
        this.workshops = new Workshop[EnrollmentManager.NUMBER_OF_WORKSHOP_SESSIONS];
        //students get something in each Workshop slot
    }

    public String[] getWorkshopPreferences() {
        return workshopPreferences;
    }


    //gets Workshop assigned to student at that session
    public Workshop getWorkshop(char session) {


    }

    public String toString() {
        return this.name;
    }

    public void setWorkshop(Workshop w, char session) {
        if (session == 'A') {
            workshopA = w;
        }
        else if (session == 'B') {
            workshopB = w;
        }

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
        if (workshopA == null || workshopB == null) {
            return true;
        }
        return false;
    }
}
