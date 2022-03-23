import java.util.ArrayList;

public class Attendee {
    private String name;
    private int grade;
    private String emailAddress;
    private String[] workshopPreferences; //in order of preference

    //references to workshops - do not instantiate in this class, only assign
    private Workshop workshopA = null;
    private Workshop workshopB = null;

    public Attendee(String name, int grade, String emailAddress, String[] workshopPreferences) {
        this.name = name;
        this.grade = grade;
        this.emailAddress = emailAddress;
        this.workshopPreferences = workshopPreferences;
    }

    public String[] getWorkshopPreferences() {
        return workshopPreferences;
    }



    public Workshop getWorkshopA() {
        return workshopA;
    }

    public Workshop getWorkshopB() {
        return workshopB;
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

    /* ADD: CHANGE ISAVAILABLE TO BE DIFFERENT FOR 8TH GRADERS */
    public boolean isAvailable() {
        if (workshopA == null || workshopB == null) {
            return true;
        }
        return false;
    }
}
