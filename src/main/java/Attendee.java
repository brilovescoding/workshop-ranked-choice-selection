import java.util.ArrayList;

public class Attendee {
    private String name;
    private int grade;
    private String emailAddress;
    private String[] workshopPreferences; //in order of preference

    //references to workshops - do not instantiate in this class, only assign
    private Workshop workshopA;
    private Workshop workshopB;

    public Attendee(String name, int grade, String emailAddress, String[] workshopPreferences) {
        this.name = name;
        this.grade = grade;
        this.emailAddress = emailAddress;
        this.workshopPreferences = workshopPreferences;
    }

}
