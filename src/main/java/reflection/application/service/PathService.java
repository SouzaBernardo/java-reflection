package reflection.application.service;

import reflection.application.exception.InvalidPathException;
import reflection.core.response.PathResponse;

import static reflection.application.enumeration.PathTypes.CONTROLLER;
import static reflection.application.util.StringUtil.capitalize;

public class PathService {
    public static final String SLASH = "/";

    public PathResponse validPath(String path, String packagePath) {

        if (!path.startsWith("/"))
            throw new InvalidPathException(path + " shouldn't start without '/'");

        var pathParts = path.trim()
                .replaceFirst(SLASH, "")
                .split(SLASH);

        String simpleClassName = capitalize(pathParts[0]);
        String fullClassName = capitalizeToController(pathParts[0], packagePath);
        if (pathParts.length == 1) {
            return new PathResponse(fullClassName, null, simpleClassName);
        }

        return new PathResponse(fullClassName, pathParts[1], simpleClassName);
    }

    private static String capitalizeToController(String className, String packagePath) {
        if (className.isBlank())
            throw new InvalidPathException(className + " cannot be blank");

        var firstCharacter = Character.toUpperCase(className.charAt(0));
        var restString = className.substring(1);

        return packagePath + firstCharacter + restString + CONTROLLER.getValue();
    }

}
