public enum WorkshopSessions {
    A('A'), B('B'), C('C');

    private final char name;

    WorkshopSessions(char name) {
        this.name = name;
    }

    public static WorkshopSessions getSessionEnumByChar(final char name)
    {
        for (WorkshopSessions type : WorkshopSessions.values())
            if (type.name == name)
                return type;

        return null;
    }
}
