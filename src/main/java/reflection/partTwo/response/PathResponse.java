package reflection.partTwo.response;

import reflection.partTwo.exception.InvalidClassNameException;

import java.util.Objects;

public class PathResponse {

    private final String className;
    private final String parameter;

    public PathResponse(String className, String parameter) {
        this.className = className;
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public String getClassName() {
        if (Objects.isNull(className) || className.isBlank())
            throw new InvalidClassNameException(className);
        return className;
    }
}
