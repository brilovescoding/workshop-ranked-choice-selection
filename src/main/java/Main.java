public class Main {
    public static void main(String[] args) {
        EnrollmentManager e = new EnrollmentManager();
        e.importData("", ""); //file names here
        e.selectWorkshopPreferencesForAttendees();
        e.printWorkshopChoices();
    }

}
