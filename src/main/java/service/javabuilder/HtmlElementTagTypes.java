package service.javabuilder;

public enum HtmlElementTagTypes {
    NAME_ONE("Name 1"),
    NAME_TWO("Name 2");

    private final String name;

    /**
     * @param name
     */
    HtmlElementTagTypes(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
