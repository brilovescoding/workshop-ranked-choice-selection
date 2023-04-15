import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import com.opencsv.*;

//class will contain methods for importing attendance and workshop data
public class EnrollmentManager {

    private static ArrayList<Workshop> workshopList;
    private  ArrayList<Attendee> attendeeList;
    private  ArrayList<Attendee> scheduledAttendees;
    private  ArrayList<Attendee> leftovers;

    public void printAttendees() {
        System.out.println(attendeeList);
    }

    public ArrayList<Attendee> getLeftovers() {
        return leftovers;
    }

    public EnrollmentManager() {
        workshopList = new ArrayList<>();
        attendeeList = new ArrayList<>();
        scheduledAttendees = new ArrayList<>();
        leftovers = new ArrayList<>();
    }
    public double calculateStandardDeviationOfAttendance(char session) {

        // finding the sum of array values
        double sum = 0.0;

        for (Workshop w: workshopList) {
            sum += w.getNumberOfAttendees(WorkshopSessions.getSessionEnumByChar(session));
        }

        // getting the mean of array.
        double mean = sum / workshopList.size();

        // calculating the standard deviation
        double standardDeviation = 0.0;
        for (Workshop w: workshopList) {
            standardDeviation += Math.pow(w.getNumberOfAttendees(WorkshopSessions.getSessionEnumByChar(session)) - mean, 2);

        }

        return Math.sqrt(standardDeviation/workshopList.size());
    }
    public static ArrayList<Workshop> getFreeTalkSessions() {
        ArrayList<Workshop> freeTalks = new ArrayList<>();
        for (Workshop w: workshopList) {
            if (w.isFreeTalk()) { freeTalks.add(w); }
        }
        return freeTalks;
    }



    public static void sortWorkshopListByAttendance(ArrayList<Workshop> listToBeSorted, WorkshopSessions session) {
        listToBeSorted.sort((o1, o2) -> (o1.getNumberOfAttendees(session) - (o2).getNumberOfAttendees(session)));
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
                    .withIgnoreQuotations(true)
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
        Column A: Name
        Column B: Description
        Column C: Location
        Column D, Faculty Moderators (separated by commas)
        Column E, Presenters (separated by commas)
        Column F, Max Attendance
        Column G, Sessions as a char array
        Column H, Is Free Talk (can accommodate students who did not pick it)
     */
    public void initializeWorkshopList(List<String[]> workshopData) {
        WorkshopFactory w = new WorkshopFactory();

        //change comma-separated strings for moderators/presenters into array of Strings
        for (String[] workshopRow: workshopData) {
            workshopList.add(w.makeWorkshop(
                    workshopRow[0],
                    workshopRow[1],
                    workshopRow[2],
                    workshopRow[3],
                    workshopRow[4],
                    Integer.parseInt(workshopRow[5]),
                    workshopRow[6].toUpperCase().toCharArray(),
                    Boolean.parseBoolean(workshopRow[7])
            ));
        }
    }

    /* Data should be organized as follows:
    Column A: Email Address
    Column B: Name
    Column C: Grade
    Columns D-H: 1st-5th preferences

     */
    public void initializeAttendeeList(List<String[]> attendeeData) {
        for (String[] attendeeRow: attendeeData) {
            attendeeList.add(new Attendee(
                    attendeeRow[0],
                    attendeeRow[1],
                    Integer.parseInt(attendeeRow[2]),
                    new String[] {attendeeRow[3], attendeeRow[4], attendeeRow[5], attendeeRow[6], attendeeRow[7]}
            ));
        }
    }

    public void scheduleEighthGraders(WorkshopSessions eighthGradeSession) {
        for (Attendee a: attendeeList) {
            if (a.getGrade() == 8) {
                a.setWorkshop(WorkshopFactory.eighthGradeMeeting, WorkshopSessions.A);
                WorkshopFactory.eighthGradeMeeting.addAttendee(WorkshopSessions.A, a);
            }
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
        for (int preferenceLevel = 1; preferenceLevel <= 5; preferenceLevel++) {
            //for each workshop, place each preference level in turn
            for (Workshop workshop : workshopList) {
                ArrayList<WorkshopSessions> availableSessions = workshop.getListOfAvailableSessions();
                //for each available workshop session
                for (WorkshopSessions sessionChar: availableSessions) {
                    ArrayList<Attendee> attendees;
                    attendees = getListOfAvailableAttendeesByPreference(workshop, sessionChar, preferenceLevel);
                    //System.out.println("Attendees who prefer " + workshop.getName() + " as preference " + preferenceLevel + " for session " + sessionChar.getChar() + ": " + attendees);
                    for (int j = workshop.getNumberOfOpenSpots(sessionChar); j > 0; j--) {
                        if (attendees.size() > 0) {
                            Attendee randomAttendee = attendees.get(new Random().nextInt(attendees.size()));
                            workshop.addAttendee(sessionChar, randomAttendee);
                            randomAttendee.setWorkshop(workshop, sessionChar);
                            attendees.remove(randomAttendee);
                            //System.out.println("Attendee scheduled: "+ randomAttendee.getName() + " for " + workshop.getName() + " on " + sessionChar.getChar());
                            if (!randomAttendee.isAvailable()) {
                                scheduledAttendees.add(randomAttendee);
                            }
                        }
                    }
                    //System.out.println("Schedule for " + workshop.getName() + " for Session" + sessionChar.getChar() + " and Preference " + preferenceLevel + ": " + workshop.getAttendees(sessionChar));
                }
            }
        }

        //gets individuals who got scheduled for no workshops,
        // i.e. aren't present in the scheduledAttendees list
        //get entire list of attendees, check each of them to see if they have an opening
        //use the method isAvailable to check for this

        for (Attendee person: attendeeList) {
            if (person.isAvailable()) {
                leftovers.add(person);
            }
        }
        System.out.println("leftovers");
        System.out.println(leftovers);
        for (Attendee a: leftovers) {
            System.out.println(a);
        }

        ArrayList<Workshop> freeTalks = getFreeTalkSessions();
        //loop through leftover attendee list to schedule each attendee one at a time
        for (int i = 0; i < leftovers.size(); i++) {
            Attendee leftover = leftovers.get(i);
            ArrayList<WorkshopSessions> availableSessions = leftover.getListOfAvailableSessions();
            //for each available workshop a student has,
            for (WorkshopSessions sessionChar : availableSessions) {

                sortWorkshopListByAttendance(freeTalks, sessionChar);
                int workshopIndex = 0;
                do  {
                    Workshop w = workshopList.get(workshopIndex);
                    if (!leftover.isStudentAlreadyInWorkshop(w)) {
                        System.out.println("adding " + leftover.getName() + " to " + w.getName());
                        w.addAttendee(sessionChar, leftover);
                        leftover.setWorkshop(w, sessionChar);
                    }
                    workshopIndex++;
                    if (workshopIndex > freeTalks.size())  {
                        throw new IndexOutOfBoundsException();
                    }
                } while ((leftover.isAvailable(sessionChar)));
            }
            //if the above code works correctly then this should remove them from the list.
            if (!leftover.isAvailable()) {
                scheduledAttendees.add(leftover);
                leftovers.remove(leftover);
                System.out.println("removing leftover " + leftover.getName());
                i--;
            }
        }
    }

    //parameter: a Workshop and a pref num that is from 1 to 5
    //return: ArrayList of attendees that listed that specific workshopID at that specific preference #
    //Attendees must also be open during that session in order to be added to that list
    public ArrayList<Attendee> getListOfAvailableAttendeesByPreference(Workshop workshop, WorkshopSessions sessionChar, int prefNum) {
        prefNum--; //for array index
        String workshopName = workshop.getName();
        ArrayList<Attendee> tempList = new ArrayList<>();

        for (Attendee attendee : this.attendeeList){
            String preference = attendee.getWorkshopPreferences()[prefNum];
            //check first to see if the name matches
            if (preference.equals(workshopName)) {
                //check if student is available for that session
                if (attendee.isAvailable(sessionChar) && !attendee.isStudentAlreadyInWorkshop(workshop)) {
                    tempList.add(attendee);
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

    public void printWorkshopChoices() {
        for (Workshop w : workshopList) {
            System.out.println(w);
        }
    }

    //Attendee data should be turned into a spreadsheet
    //The following columns will be used:
    //Column A: Email Address
    //Column B: Attendee Name
    //Column C: Attendee Grade
    //Column D: Attendee's Workshop A name
    //Column E: Attendee's Workshop A location
    //Column F: Attendee's Workshop A description
    //Column G: Attendee's Workshop B name
    //Column H: Attendee's Workshop B location
    //Column I: Attendee's Workshop B description
    public void convertAttendeeDataToCSV() throws Exception {
        List<String[]> attendeeFinalData = new ArrayList<>();
        for (Attendee attendee : scheduledAttendees) {
            ArrayList<String> dataRow = new ArrayList<>();
            dataRow.add(attendee.getEmailAddress());
            dataRow.add(attendee.getName());
            dataRow.add(Integer.toString(attendee.getGrade()));

            for (WorkshopSessions wss: WorkshopSessions.values()) {
                Workshop w = attendee.getWorkshop(wss);
                //if workshop exists, add workshop info
                if (w != null) {
                    dataRow.add(w.getName());
                    dataRow.add(w.getLocation());
                    dataRow.add(w.getDescription());
                }
                else {
                    int NUM_COLS_FOR_WORKSHOP = 3;
                    for (int i = 0; i < NUM_COLS_FOR_WORKSHOP; i++) {
                        dataRow.add("None");
                    }
                }
            }

            for (Workshop w: attendee.getWorkshops().values()) {
                dataRow.add(w.getName());
                dataRow.add(w.getLocation());
                dataRow.add(w.getDescription());
            }
            attendeeFinalData.add(dataRow.toArray(new String[0]));
        }

        //convert list of Attendees to a list of Strings
        String filePath = "data/attendeeresults.tsv";
        FileWriter writer = new FileWriter(filePath);
        CSVParser parser = new CSVParserBuilder().build();
        ICSVWriter csvParserWriter = new CSVWriterBuilder(writer)
                .withParser(parser)
                .withLineEnd(ICSVWriter.RFC4180_LINE_END)
                .build(); // will produce a CSVParserWriter

        ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                .withSeparator('\t')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build(); // will produce a CSVWriter


        csvWriter.writeAll(attendeeFinalData);
        csvWriter.close();
    }

    //Workshop data should be turned into a spreadsheet for attendance purposes
    //Two separate columns should occur for both A and B sessions
    //The following columns will be used:
    //Column A: Email Address of Moderator
    //Column B: Workshop Name
    //Column C: Workshop URL
    //Column D: Presenter
    //Column E: Workshop Session A Attendees String
    //Column F: Workshop Session A Attendance #
    //Column G: Workshop Session B Attendees String
    //Column H: Workshop Session B Attendance #
    public void convertWorkshopDataToCSV() throws Exception {
        List<String[]> workshopFinalData = new ArrayList<>();

        for (Workshop workshop : workshopList) {

            ArrayList<String> dataRow = new ArrayList<>();
            dataRow.add(workshop.getModerators());
            dataRow.add(workshop.getName());
            dataRow.add(workshop.getLocation());
            dataRow.add(workshop.getPresenters());

            //for each active session
            for (WorkshopSessions wss: WorkshopSessions.values()) {
                //if not null and there is someone attending,
                ArrayList<Attendee> attendees = workshop.getAttendees(wss);
                if (attendees != null && attendees.size() > 0) {
                    StringBuilder stringifier = new StringBuilder();
                    for (Attendee attendee: attendees) {
                        stringifier.append(attendee.getName()).append(";");
                    }
                    dataRow.add(stringifier.toString());
                    dataRow.add(String.valueOf(attendees.size()));
                }
                else {
                    dataRow.add("Empty");
                    dataRow.add("Empty");
                }

            }

            workshopFinalData.add(dataRow.toArray(new String[0]));

        }

        //convert list of Attendees to a list of Strings
        String filePath = "data/workshopresults.tsv";
        FileWriter writer = new FileWriter(filePath);
        CSVParser parser = new CSVParserBuilder().build();
        ICSVWriter csvParserWriter = new CSVWriterBuilder(writer)
                .withParser(parser)
                .withLineEnd(ICSVWriter.RFC4180_LINE_END)
                .build(); // will produce a CSVParserWriter

        ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                .withSeparator('\t')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build(); // will produce a CSVWriter


        csvWriter.writeAll(workshopFinalData);
        csvWriter.close();
    }

    //Attendee data should be turned into a spreadsheet
    //The following columns will be used:
    //Column A: Email Address
    //Column B: Attendee Name
    //Column C: Attendee Grade
    //Column D: Attendee's Workshop A name and URL
    //Column E: Attendee's Workshop B name and URL
    public void convertLeftoverDataToCSV() throws Exception {
        List<String[]> attendeeFinalData = new ArrayList<>();

        for (Attendee attendee : leftovers) {

            ArrayList<String> dataRow = new ArrayList<>();
            dataRow.add(attendee.getEmailAddress());
            dataRow.add(attendee.getName());
            dataRow.add(Integer.toString(attendee.getGrade()));
            for (WorkshopSessions wss: WorkshopSessions.values()) {
                Workshop w = attendee.getWorkshop(wss);
                //if workshop exists, add workshop info
                if (w != null) {
                    dataRow.add(w.getName());
                    dataRow.add(w.getLocation());
                    dataRow.add(w.getDescription());
                }
                else {
                    int NUM_COLS_FOR_WORKSHOP = 3;
                    for (int i = 0; i < NUM_COLS_FOR_WORKSHOP; i++) {
                        dataRow.add("None");
                    }
                }
            }

            attendeeFinalData.add(dataRow.toArray(new String[0]));

        }

        //convert list of Attendees to a list of Strings
        String filePath = "data/leftovers.tsv";
        FileWriter writer = new FileWriter(filePath);
        CSVParser parser = new CSVParserBuilder().build();
        ICSVWriter csvParserWriter = new CSVWriterBuilder(writer)
                .withParser(parser)
                .withLineEnd(ICSVWriter.RFC4180_LINE_END)
                .build(); // will produce a CSVParserWriter

        ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                .withSeparator('\t')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build(); // will produce a CSVWriter


        csvWriter.writeAll(attendeeFinalData);
        csvWriter.close();
    }
}

