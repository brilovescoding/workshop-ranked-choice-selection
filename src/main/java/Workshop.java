public abstract class Workshop {
    private int id;
    private String name;
    private String description;
    private String url;
    private String moderators;
    private String presenters;
    private int maxAttendance;
    private boolean isFreeTalk;

    public Workshop(int id, String name, String description, String url, String moderators, String presenters, int maxAttendance, boolean isFreeTalk) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.moderators = moderators;
        this.presenters = presenters;
        this.maxAttendance = maxAttendance;
        this.isFreeTalk = isFreeTalk;
    }
    public String toString() {
        return "Name: " + name + "\nDescription: " + description + "\nURL: " + url + "\nPresenters: " + presenters + "\nModerators: " + moderators + "\n";
    }

    public int getMaxAttendance() {
        return maxAttendance;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getModerators() {
        return moderators;
    }

    public String getPresenters() {
        return presenters;
    }
}
