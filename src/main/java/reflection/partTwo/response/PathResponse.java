package reflection.partTwo.response;

import reflection.partTwo.exception.InvalidClassNameException;

import java.util.Objects;

public class PathResponse {

    private final String className;
    private final String method;
    private final String controllerName;

    public PathResponse(String className, String method, String controllerName) {
        this.className = className;
        this.method = method;
        this.controllerName = controllerName;
    }

    public String getMethod() {
        return method;
    }

    public String getClassName() {
        if (Objects.isNull(className) || className.isBlank())
            throw new InvalidClassNameException(className);
        return className;
    }
}
