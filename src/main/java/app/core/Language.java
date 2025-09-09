package app.core;

public enum Language {
    EN("English"),
    ES("Spanish");

    private final String display;
    Language(String d) { this.display = d; }
    public String displayName() { return display; }
}
