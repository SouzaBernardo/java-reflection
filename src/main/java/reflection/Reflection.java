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
}
