import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    //method takes in a filepath, imports the file into a List of String arrays,
    // (one for each row), and changes each into a list of Workshops and Attendees
    //Workshops will utilize the WorkshopFactory
    public void importData(String workshopCSVFilePath, String attendeeCSVFilePath) {
        List<String[]> workshopData = getListFromCSV(workshopCSVFilePath);
        List<String[]> attendeeData = getListFromCSV(attendeeCSVFilePath);

        //loop through attendee data, instantiate objects for each attendee

        //loop through workshopData; instantiate objects for workshopList
        //order of column in spreadsheet are:
    }

    public List<String[]> getListFromCSV(String filepath) {
        try {
            CSVReader reader = new CSVReader(new FileReader(filepath));
            return reader.readAll();
        } catch (Exception e) { System.out.println("Error reading in file."); }

        return null;
    }

    /*
    iterate through each Workshop
    for each workshop, form a list of attendees that have their first preference to be that workshop (may
    match by IDs)

     */
    public void selectWorkshopPreferences() {

    }

    //parameter: a pref num that is from 1 - 5
    //return: ArrayList of attendees that listed that specific workshopID at that specific preference #
    //Attendees must also be open during that timeslot in order to be added to that list
    public ArrayList<Attendee> getListOfAttendeesByPreference(int workshopID, int prefNum, char timeSlot) {
        prefNum--; //to work with array indexes

        return null;
    }

}
