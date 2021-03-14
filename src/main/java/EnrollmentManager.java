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

    //important: CSV files should use **TAB SEPARATED VALUES** to avoid issues with commas in Workshop descriptions and lists of moderators/presenters
    public List<String[]> getListFromCSV(String filepath) {
        try {
            CSVReader reader = new CSVReader(new FileReader(filepath));
            return reader.readAll();
        } catch (Exception e) { System.out.println("Error reading in file."); }

        return null;
    }

    /*
    workshop spreadsheet format should be:
        Column A: ID
        Column B: Name
        Column C: Description
        Column D: URL
        Column E, Faculty Moderators (separated by commas)
        Column F, Presenters (separated by commas)
        Column G, Type (either TALK or SEMINAR)
        Column H, Sessions (either A, B, or AB)
     */
    public void initializeWorkshopList(List<String[]> workshopData) {
        WorkshopFactory w = new WorkshopFactory();

        //change comma-separated strings for moderators/presenters into array of Strings
        for (String[] workshop: workshopData) {
            workshopList.add(w.makeWorkshop(
                    Integer.parseInt(workshop[0]),
                    workshop[1],
                    workshop[2],
                    workshop[3],
                    workshop[4].split("[,]", 0),
                    workshop[5].split("[,]", 0),
                    workshop[6].toUpperCase(),
                    workshop[7].toUpperCase()
            ));
        }


    }

    /*
    iterate through each Workshop
    for each workshop, form a list of attendees that have their first preference to be that workshop (may
    match by IDs)

     */
    public void selectWorkshopPreferencesForAttendees() {

    }

    //parameter: a pref num that is from 1 - 5
    //return: ArrayList of attendees that listed that specific workshopID at that specific preference #
    //Attendees must also be open during that timeslot in order to be added to that list
    public ArrayList<Attendee> getListOfAttendeesByPreference(int workshopID, int prefNum, char timeSlot) {
        prefNum--; //to work with array indexes

        return null;
    }



}
