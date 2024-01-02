package reflection;


public class Reflection {

    public ClassManipulator reflectionClass(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return new ClassManipulator(aClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
