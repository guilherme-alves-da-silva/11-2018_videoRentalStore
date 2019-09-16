/**
 * first time using enums.
 */
public enum Separator {
    STANDARD(" - ");

    private final String value;

    Separator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
