public enum WorkshopSessions
{
    A('A'), B('B'), C('C');

    private final char name;

    WorkshopSessions(char name) {
        this.name = name;
    }

    public static WorkshopSessions getSessionEnumByChar(final char n)
    {
        for (WorkshopSessions type : WorkshopSessions.values())
            if (type.name == n)
                return type;

        return null;
    }

    public char getChar() {
        return name;
    }
}
