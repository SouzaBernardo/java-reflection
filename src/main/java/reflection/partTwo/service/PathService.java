package reflection.partTwo.service;

import reflection.partTwo.exception.InvalidPathException;
import reflection.partTwo.response.PathResponse;

public class PathService {

    public static final String CONTROLLER = "Controller";
    public static final String DOMAIN = "reflection.partTwo.domain.";
    public static final String CONTROLLER_PATH = "reflection.partTwo.controller.";
    public static final String SLASH = "/";

    public PathResponse validPath(String path) {

        if (!path.startsWith("/"))
            throw new InvalidPathException(path + " shouldn't start without '/'");

        var pathParts = path.trim()
                .replaceFirst(SLASH, "")
                .split(SLASH);

        String className = capitalize(pathParts[0]);
        if (pathParts.length == 1) {
            return new PathResponse(className, null, null);
        }

        return new PathResponse(className, pathParts[1], null);
    }

    public PathResponse validPathController(String path) {

        if (!path.startsWith("/"))
            throw new InvalidPathException(path + " shouldn't start without '/'");

        var pathParts = path.trim()
                .replaceFirst(SLASH, "")
                .split(SLASH);
        String simpleClassName = capitalize(pathParts[0]);
        String classNameController = capitalizeToController(pathParts[0]);
        if (pathParts.length == 1) {
            return new PathResponse(classNameController, null, classNameController);
        }

        return new PathResponse(classNameController, pathParts[1], classNameController);
    }

    private static String capitalizeToController(String className) {
        if (className.isBlank())
            throw new InvalidPathException(className + " cannot be blank");


        var firstCharacter = Character.toUpperCase(className.charAt(0));
        var restString = className.substring(1);

        return CONTROLLER_PATH + firstCharacter + restString + CONTROLLER;
    }

    private static String capitalize(String className) {
        if (className.isBlank())
            throw new InvalidPathException(className + " cannot be blank");


        var firstCharacter = Character.toUpperCase(className.charAt(0));
        var restString = className.substring(1);

        return DOMAIN + firstCharacter + restString;
    }

}
