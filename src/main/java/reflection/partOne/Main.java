package reflection.partOne;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class Main {

    public static final String REFLECTION_DOG = "reflection.partOne.Dog";
    public static final String METHOD_NAME = "sayHiTo";

    public static void main(String[] args) {
        try {
            exampleGetDeclaredConstructors();
            exampleConstructorByBuilder();
            exampleMethod();
            exampleMethodWithParameters();
            exampleInvokeWithMethodManipulator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void exampleInvokeWithMethodManipulator() {
        var instance = Reflection
                .reflect(REFLECTION_DOG)
                .constructor("Little Dog")
                .useMethod()
                .handleException((method, error) -> {
                    System.out.println("Error on method: " + error.getMessage());
                    throw new RuntimeException("Error on method: " + method);
                })
                .invoke(METHOD_NAME,  "Vii");
        System.out.println(instance);
    }

    private static void exampleMethodWithParameters() throws InvocationTargetException, IllegalAccessException {
        var myClass = Reflection
                .reflect(REFLECTION_DOG)
                .getReflectedClass();

        var instance = Reflection
                .reflect(REFLECTION_DOG)
                .constructor(String.class)
                .build("Little Dog");

        var list = Stream.of(myClass.getMethods())
                .filter(method -> METHOD_NAME.equals(method.getName()))
                .toList();

        System.out.println(list.get(0).invoke(instance, "Jorge"));

    }

    private static void exampleMethod() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        var myClass = Class.forName(REFLECTION_DOG);

        var declaredMethod = myClass.getDeclaredMethods()[0];
        var invoke = Reflection
                .reflect(REFLECTION_DOG)
                .constructor()
                .build();
        System.out.println(declaredMethod.invoke(invoke));
    }

    private static void exampleConstructorByBuilder() {
        var invoke = Reflection
                .reflect(REFLECTION_DOG)
                .constructor()
                .build();
        System.out.println(invoke);
    }

    private static void exampleGetDeclaredConstructors() throws ClassNotFoundException,
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