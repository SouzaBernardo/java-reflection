package reflection.partTwo.response;

import reflection.partTwo.exception.InvalidClassNameException;

import java.util.Objects;

public class PathResponse {

    private final String fullClassName;
    private final String method;
    private final String controllerName;

    public PathResponse(String fullClassName, String method, String controllerName) {
        this.fullClassName = fullClassName;
        this.method = method;
        this.controllerName = controllerName;
    }

    public String getMethod() {
        return method;
    }

    public String getFullClassName() {
        if (Objects.isNull(fullClassName) || fullClassName.isBlank())
            throw new InvalidClassNameException(fullClassName);
        return fullClassName;
    }
}
