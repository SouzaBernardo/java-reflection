package reflection.partTwo.service;

import reflection.partTwo.exception.InvalidPathException;
import reflection.partTwo.response.PathResponse;

public class PathService {

    public static final String SLASH = "/";

    public static PathResponse validPath(String path) {

        if (!path.startsWith("/"))
            throw new InvalidPathException(path + " shouldn't start without '/'");

        var pathParts = path.trim()
                .replaceFirst(SLASH, "")
                .split(SLASH);

        String className = capitalize(pathParts[0]);
        if (pathParts.length == 1) {
            return new PathResponse(className, null);
        }

        return new PathResponse(className, pathParts[1]);
    }

    private static String capitalize(String className) {
        if (className.isBlank())
            throw new InvalidPathException(className+ " cannot be blank");


        var firstCharacter = Character.toUpperCase(className.charAt(0));
        var restString = className.substring(1);

        return firstCharacter + restString;
    }

}
