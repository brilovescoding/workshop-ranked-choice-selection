import java.util.ArrayList;
import java.util.HashMap;

public class WorkshopFactory {

    public Workshop makeWorkshop(String name, String description, String location, String moderators, String presenters, int maxAttendance, char[] sessions, boolean isFreeTalk) {

        //for each character in sessions, create a Workshop with that character as a key
        HashMap<WorkshopSessions, ArrayList<Attendee>> attendees = new HashMap<WorkshopSessions, ArrayList<Attendee>>();

        for (char sessionChar: sessions) {
            attendees.put(WorkshopSessions.getSessionEnumByChar(sessionChar), new ArrayList<Attendee>());
        }

        return new Workshop(name, description, location, moderators, presenters, maxAttendance, isFreeTalk, attendees);

    }
}
