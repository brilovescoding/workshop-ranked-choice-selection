public class Main {
    public static void main(String[] args) {
        EnrollmentManager e = new EnrollmentManager();
        e.importData("data/workshops.tsv", "data/attendees.tsv"); //file names here
        e.selectWorkshopPreferencesForAttendees();
        e.printWorkshopChoices();
        try {
            e.convertAttendeeDataToCSV();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

}
