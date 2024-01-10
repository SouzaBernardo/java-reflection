package reflection.core.service;


public class Reflection {

    public static ClassManipulator reflect(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return new ClassManipulator(aClass);
        } catch (ClassNotFoundException e) {
            String errorMessage = "Error on Reflect class with name >> " + className;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public static ClassManipulator reflect(Class<?> aClass) {
            return new ClassManipulator(aClass);
    }
}
