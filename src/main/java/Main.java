public class Main {
    public static void main(String[] args) {
        EnrollmentManager e = new EnrollmentManager();
        e.importData("data/workshops.tsv", "data/attendees2.tsv"); //file names here
        e.printAttendees();
        e.selectWorkshopPreferencesForAttendees();
        e.printWorkshopChoices();
    }

}
