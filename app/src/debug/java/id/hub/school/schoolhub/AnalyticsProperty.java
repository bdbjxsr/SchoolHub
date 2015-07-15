package id.hub.school.schoolhub;

public enum AnalyticsProperty {
    PRODUCTION("production", "UA-65161627-1"),
    DEVELOPMENT("development", "UA-65161627-2");

    private final String key;
    private final String value;

    AnalyticsProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() { return value; }
}
