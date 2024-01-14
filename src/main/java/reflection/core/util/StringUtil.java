package reflection.core.util;

import reflection.application.exception.InvalidPathException;

public class StringUtil {
    public static String capitalize(String className) {
        if (className.isBlank())
            throw new InvalidPathException(className + " cannot be blank");

        var firstCharacter = Character.toUpperCase(className.charAt(0));
        var restString = className.substring(1);

        return firstCharacter + restString;
    }
}
