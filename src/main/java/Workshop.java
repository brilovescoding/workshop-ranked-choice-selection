public abstract class Workshop {
    private String name;
    private String description;
    private String location;
    private String moderators;
    private String presenters;
    private int maxAttendance;
    private boolean isFreeTalk;

    public Workshop(String name, String description, String location, String moderators, String presenters, int maxAttendance, boolean isFreeTalk) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.moderators = moderators;
        this.presenters = presenters;
        this.maxAttendance = maxAttendance;
        this.isFreeTalk = isFreeTalk;
    }
    public String toString() {
        return "Name: " + name + "\nDescription: " + description + "\nLocation: " + location + "\nPresenters: " + presenters + "\nModerators: " + moderators + "\n";
    }

    public int getMaxAttendance() {
        return maxAttendance;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getModerators() {
        return moderators;
    }

    public String getPresenters() {
        return presenters;
    }

    public boolean isFreeTalk() {
        return isFreeTalk;
    }

    public String getDescription() {
        return description;
    }
}
