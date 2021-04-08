public abstract class Workshop {
    private int id;
    private String name;
    private String description;
    private String url;
    private String moderators;
    private String presenters;
    private String type;
    private int maxAttendance;
    public static final int MAX_ATTENDEES_SEMINAR = 25;
    public static final int MAX_ATTENDEES_TALK = 250;

    public Workshop(int id, String name, String description, String url, String moderators, String presenters, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.moderators = moderators;
        this.presenters = presenters;
        this.type = type;
        System.out.println(type);
        if (this.type.equals("TALK")) {
            maxAttendance = MAX_ATTENDEES_TALK;
        }
        else if (this.type.equals("SEMINAR")){
            maxAttendance = MAX_ATTENDEES_SEMINAR;
        }
        else {
            System.out.println("Error, person is not getting max Attendance set properly");
        }
    }

    public int getMaxAttendance() {
        return maxAttendance;
    }
    public String toString() {
        return "Name: " + name + "\nDescription: " + description + "\nURL: " + url + "\nPresenters: " + presenters + "\nModerators: " + moderators + "\nType: " + type + "\n";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
