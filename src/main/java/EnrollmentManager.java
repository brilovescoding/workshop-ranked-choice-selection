import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        //loop through workshopData; instantiate objects for workshopList
        initializeWorkshopList(workshopData);

        //loop through attendeeData, instantiate objects for each attendee
        initializeAttendeeList(attendeeData);

    }

    //important: CSV files should use **TAB SEPARATED VALUES** to avoid issues with commas in Workshop descriptions and lists of moderators/presenters
    public List<String[]> getListFromCSV(String filepath) {
        try {
            final CSVParser parser = new CSVParserBuilder()
                    .withSeparator('\t')
                    .build();
            final CSVReader reader = new CSVReaderBuilder(new FileReader(filepath))
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();
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
        for (String[] workshopRow: workshopData) {
            workshopList.add(w.makeWorkshop(
                    Integer.parseInt(workshopRow[0]),
                    workshopRow[1],
                    workshopRow[2],
                    workshopRow[3],
                    workshopRow[4],
                    workshopRow[5],
                    workshopRow[6].toUpperCase(),
                    workshopRow[7].toUpperCase()
            ));
        }
    }

    /* Data should be organized as follows:
    Column A: Name
    Column B: Grade
    Column C: Email Address
    Columns D-H: 1st-5th preferences

     */
    public void initializeAttendeeList(List<String[]> attendeeData) {
        for (String[] attendeeRow: attendeeData) {
            attendeeList.add(new Attendee(
                    attendeeRow[0],
                    Integer.parseInt(attendeeRow[1]),
                    attendeeRow[2],
                    new String[] {attendeeRow[3], attendeeRow[4], attendeeRow[5], attendeeRow[6], attendeeRow[7]}
            ));
        }
    }

    /*
    iterate through each Workshop
    for each workshop, form a list of attendees that have their first preference to be that workshop (may
    match by IDs)

    push to Workshop
     */
    public void selectWorkshopPreferencesForAttendees() {

        //for each preference level
        for (int i = 1; i <= 5; i++) {
            //for each workshop, place each preference level in turn
            for (Workshop workshop : workshopList) {
                //get the attendees who listed this workshop as the one they wanted
                //match the name they put for the first item in attendance.workshopPreferences to the
                //name of the given workshop
                ArrayList<Attendee> attendees = getListOfAttendeesByPreference(workshop, i);
                if (workshop instanceof SingleSessionWorkshop) {
                    SingleSessionWorkshop s = (SingleSessionWorkshop) workshop;
                    for (int j = 0; i < Workshop.MAX_ATTENDEES; i++) {
                        Attendee randomAttendee = attendees.get(new Random().nextInt(attendees.size()));
                        s.addAttendee(randomAttendee);
                    }
                }
                else if (workshop instanceof DoubleSessionWorkshop) {
                    DoubleSessionWorkshop d = (DoubleSessionWorkshop) workshop;
                    //first handle Session A
                    for (int j = 0; j < Workshop.MAX_ATTENDEES; j++) {
                        Attendee randomAttendee = attendees.get(new Random().nextInt(attendees.size()));
                        d.addAttendee(randomAttendee, 'A');
                    }

                    //then Session B
                    for (int j = 0; j < Workshop.MAX_ATTENDEES; j++) {
                        Attendee randomAttendee = attendees.get(new Random().nextInt(attendees.size()));
                        d.addAttendee(randomAttendee, 'B');
                    }
                }

            }
        }
    }

    //parameter: a Workshop and a pref num that is from 1 - 5
    //return: ArrayList of attendees that listed that specific workshopID at that specific preference #
    //Attendees must also be open during that timeslot in order to be added to that list
    public ArrayList<Attendee> getListOfAttendeesByPreference(Workshop workshop, int prefNum) {
        prefNum--; //for array index
        String workshopName = workshop.getName();
        ArrayList<Attendee> tempList = new ArrayList<Attendee>();

        for (Attendee attendee : this.attendeeList){
            String preference = attendee.getWorkshopPreferences()[prefNum];
            //check first to see if the name matches
            if (preference.equals(workshopName)) {
                //if single session, check if student is available for that session
                if (workshop instanceof SingleSessionWorkshop) {
                    SingleSessionWorkshop s = (SingleSessionWorkshop) workshop;
                    if (s.getSession() == 'A' && attendee.getWorkshopA() == null) {
                        tempList.add(attendee);
                    } else if (s.getSession() == 'B' && attendee.getWorkshopB() == null) {
                        tempList.add(attendee);
                    }
                }
                //add Attendee ONCE to the list if they are available for either A or B
                else if (workshop instanceof DoubleSessionWorkshop) {
                    DoubleSessionWorkshop d = (DoubleSessionWorkshop) workshop;
                    if (attendee.getWorkshopA() == null || attendee.getWorkshopB() == null) {
                        tempList.add(attendee);
                    }
                }
            }
        }

        return tempList;
    }

    public Workshop getWorkshopByName(String name) {
        for (Workshop workshop : workshopList) {
            if (workshop.getName().equals(name))
                return workshop;
        }
        return null;
    }





}
