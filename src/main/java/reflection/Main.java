package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static final String REFLECTION_DOG = "reflection.Dog";

    public static void main(String[] args) {
        try {
            exampleConstructors();
            exampleConstructorByBuilder();
            exampleMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void exampleMethod() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        var myClass = Class.forName(REFLECTION_DOG);

        for (Method method : myClass.getMethods()) {
            System.out.println(method);
        }

        var declaredMethod = myClass.getDeclaredMethods()[0];
        var invoke = new Reflection()
                .reflectionClass(REFLECTION_DOG)
                .getDefaultConstructor()
                .invoke();
        System.out.println(declaredMethod.invoke(invoke));
    }

    private static void exampleConstructorByBuilder() {
        var invoke = new Reflection()
                .reflectionClass(REFLECTION_DOG)
                .getDefaultConstructor()
                .invoke();
        System.out.println(invoke);
    }

    private static void exampleConstructors() throws ClassNotFoundException,
                                                    NoSuchMethodException,
                                                    InstantiationException,
                                                    IllegalAccessException,
                                                    InvocationTargetException {
        var dogClass = Class.forName(REFLECTION_DOG);
        var simpleConstructor = dogClass.getDeclaredConstructor();
        var constructorWithString = dogClass.getDeclaredConstructor(String.class);

        var dogao = simpleConstructor.newInstance();
        var dog = constructorWithString.newInstance("Dog Dog");

        System.out.println(dogao);
        System.out.println(dog);
    }
}