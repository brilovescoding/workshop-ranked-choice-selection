import java.util.ArrayList;

public class Attendee {
    private String name;
    private int grade;
    private String emailAddress;

    public String[] getWorkshopPreferences() {
        return workshopPreferences;
    }

    private String[] workshopPreferences; //in order of preference

    public Workshop getWorkshopA() {
        return workshopA;
    }

    public void setWorkshopA(Workshop workshopA) {
        this.workshopA = workshopA;
    }

    public Workshop getWorkshopB() {
        return workshopB;
    }

    public void setWorkshopB(Workshop workshopB) {
        this.workshopB = workshopB;
    }

    //references to workshops - do not instantiate in this class, only assign
    private Workshop workshopA = null;
    private Workshop workshopB = null;

    public Attendee(String name, int grade, String emailAddress, String[] workshopPreferences) {
        this.name = name;
        this.grade = grade;
        this.emailAddress = emailAddress;
        this.workshopPreferences = workshopPreferences;
    }

    public String toString() {
        return this.name;
    }

}
