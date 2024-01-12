package reflection.core.service;


public class Reflection {

    public static ClassManipulatorImpl reflect(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return new ClassManipulatorImpl(aClass);
        } catch (ClassNotFoundException e) {
            String errorMessage = "Error on Reflect class with name >> " + className;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public static ClassManipulatorImpl reflect(Class<?> aClass) {
            return new ClassManipulatorImpl(aClass);
    }
}
