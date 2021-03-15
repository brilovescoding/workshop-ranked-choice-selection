public abstract class Workshop {
    private int id;
    private String name;
    private String description;
    private String url;
    private String moderators;
    private String presenters;
    private String type;

    public static final int MAX_ATTENDEES = 15;

    public Workshop(int id, String name, String description, String url, String moderators, String presenters, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.moderators = moderators;
        this.presenters = presenters;
        this.type = type;
    }

    public String toString() {
        return "Name: " + name + "\nDescription" + description + "\nURL: " + url + "\nPresenters: " + presenters + "\nModerators" + moderators + "\nType: " + type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public String getType() {
        return type;
    }
}
