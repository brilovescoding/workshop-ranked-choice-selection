import java.util.ArrayList;
import com.opencsv.*;

//class will contain methods for importing attendance and workshop data
public class EnrollmentManager {

    private ArrayList<Workshop> workshopList;
    private ArrayList<Attendee> attendeeList;
    private ArrayList<Workshop> completedWorkshopEnrollment;

    public EnrollmentManager() {
        workshopList = new ArrayList<Workshop>();
        attendeeList = new ArrayList<Attendee>();
        completedWorkshopEnrollment = new ArrayList<Workshop>();
    }



}
