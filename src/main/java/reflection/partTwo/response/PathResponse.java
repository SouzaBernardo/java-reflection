package reflection.partTwo.response;

import reflection.core.exception.InvalidClassNameException;
import reflection.core.exception.InvalidMethodException;

import static java.util.Objects.isNull;

public class PathResponse {

    public static final String GET = "get";
    private final String fullClassName;
    private final String method;
    private final String simpleClassName;

    public PathResponse(String fullClassName, String method, String simpleClassName) {
        this.fullClassName = fullClassName;
        this.method = method;
        this.simpleClassName = simpleClassName;
    }

    public String getMethod() {
        if (isNull(method)) {
            throw new InvalidMethodException();
        }

        try {
            Long.parseLong(method);
            return GET + simpleClassName;
        } catch (NumberFormatException e) {
            return method;
        }
    }

    public String getParameter() {
        return method;
    }

    public String getFullClassName() {
        if (isNull(fullClassName) || fullClassName.isBlank())
            throw new InvalidClassNameException(fullClassName);
        return fullClassName;
    }
}
