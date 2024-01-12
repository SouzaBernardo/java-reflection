package reflection.application.service;

import reflection.core.exception.InvalidPathException;
import reflection.core.response.PathResponse;

import static reflection.core.util.StringUtil.capitalize;

public class PathService {

    // TODO: Add enum DOMAIN and CONTROLLER
    public static final String CONTROLLER = "Controller";
//    public static final String DOMAIN = "reflection.core.domain.";
    public static final String CONTROLLER_PATH = "reflection.controller.";
    public static final String SLASH = "/";

    public PathResponse validPath(String path) {

        if (!path.startsWith("/"))
            throw new InvalidPathException(path + " shouldn't start without '/'");

        var pathParts = path.trim()
                .replaceFirst(SLASH, "")
                .split(SLASH);

        String simpleClassName = capitalize(pathParts[0]);
        String fullClassName = capitalizeToController(pathParts[0]);
        if (pathParts.length == 1) {
            return new PathResponse(fullClassName, null, simpleClassName);
        }

        return new PathResponse(fullClassName, pathParts[1], simpleClassName);
    }

    private static String capitalizeToController(String className) {
        if (className.isBlank())
            throw new InvalidPathException(className + " cannot be blank");

        var firstCharacter = Character.toUpperCase(className.charAt(0));
        var restString = className.substring(1);

        return CONTROLLER_PATH + firstCharacter + restString + CONTROLLER;
    }

}