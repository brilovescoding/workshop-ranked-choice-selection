import java.util.ArrayList;

public class Attendee {
    private String name;
    private int grade;
    private String emailAddress;
    private String[] workshopPreferences; //in order of preference

    //references to workshops - do not instantiate in this class, only assign
    private Workshop workshopA = null;
    private Workshop workshopB = null;

    private static SingleSessionWorkshop eighthGradeWorkshop = new SingleSessionWorkshop("8th Grade Meeting", "Eighth Grade Meeting for Session B", "location TBD", "Ms. Salzano", "Ms Salzano", 999, 'B', false);

    public Attendee(String emailAddress, String name, int grade, String[] workshopPreferences) {
        this.name = name;
        this.grade = grade;
        this.emailAddress = emailAddress;
        this.workshopPreferences = workshopPreferences;
        if (this.grade == 8) {
            workshopB = eighthGradeWorkshop;
            eighthGradeWorkshop.addAttendee(this);
        }
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

    public boolean isAvailable() {
        if (workshopA == null || workshopB == null) {
            return true;
        }
        return false;
    }
}
