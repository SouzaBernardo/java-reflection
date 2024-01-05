package reflection.partOne;


public class Reflection {

    public static ClassManipulator reflection(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return new ClassManipulator(aClass);
        } catch (ClassNotFoundException e) {
            String errorMessage = "Error on Reflect class with name >> " + className;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
