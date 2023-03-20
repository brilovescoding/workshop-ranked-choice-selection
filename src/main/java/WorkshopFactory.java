public class WorkshopFactory {

    public WorkshopFactory() { }

    public Workshop makeWorkshop(String name, String description, String url, String moderators, String presenters, int maxAttendance, String sessions, boolean isFreeTalk) {

        //for each character in sessions, create a Workshop with that character as a key
        Workshop w = new Workshop();
        char[] sessionChars = sessions.toCharArray();

        for (char sessionChar: sessionChars) {

        }

        return w;

        return null;
    }

}
