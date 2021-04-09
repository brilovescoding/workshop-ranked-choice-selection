import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final int NUMBER_OF_SIMULATIONS = 1;
        ArrayList<EnrollmentManager> enrollments = new ArrayList<EnrollmentManager>();
        for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++) {
            EnrollmentManager e = new EnrollmentManager();
            e.importData("data/workshops.tsv", "data/attendees.tsv"); //file names here
            e.selectWorkshopPreferencesForAttendees();
            //e.printWorkshopChoices();
            enrollments.add(e);
        }

        int[] enrollmentSizes = new int[NUMBER_OF_SIMULATIONS];
        for (int i = 0; i < enrollmentSizes.length; i++) {
            enrollmentSizes[i] = enrollments.get(i).getLeftovers().size();
        }
        int smallest = indexOfSmallest(enrollmentSizes);
        System.out.println("Smallest I could find is " + enrollmentSizes[smallest] + " leftover students.");
        try {
            EnrollmentManager e = enrollments.get(smallest);
            e.convertAttendeeDataToCSV();
            e.convertWorkshopDataToCSV();
            e.convertLeftoverDataToCSV();
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
        }
    }
    //stolen from StackExchange!
    public static int indexOfSmallest(int[] array){

        // add this
        if (array.length == 0)
            return -1;

        int index = 0;
        int min = array[index];

        for (int i = 1; i < array.length; i++){
            if (array[i] <= min){
                min = array[i];
                index = i;
            }
        }
        return index;
    }
}
