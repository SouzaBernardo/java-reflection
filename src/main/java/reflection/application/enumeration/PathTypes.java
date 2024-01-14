package reflection.application.enumeration;

public enum PathTypes {
    CONTROLLER("Controller"), DOMAIN("Domain");

    private final String value;

    PathTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
