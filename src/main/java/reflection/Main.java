package reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class Main {

    public static final String REFLECTION_DOG = "reflection.Dog";

    public static void main(String[] args) {
        try {
            exampleConstructors();
            exampleConstructorByBuilder();
            exampleMethod();
            exampleMethodWithParameters();
            exampleInvokeWithMethodManipulator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void exampleInvokeWithMethodManipulator() {
        var instance = new Reflection()
                .reflectionClass(REFLECTION_DOG)
                .getDefaultConstructorWithStringParameter()
                .invokeWithParameter("Little dog")
                .handleException((method, error) -> {
                    System.out.println("Error on method: " + method.getName());
                    throw new RuntimeException(error);
                });

        System.out.println(instance.invoke("sayHiTo", "Vi"));
    }

    private static void exampleMethodWithParameters() throws InvocationTargetException, IllegalAccessException {
        var myClass = new Reflection()
                .reflectionClass(REFLECTION_DOG)
                .getReflectedClass();

        var instance = new Reflection()
                .reflectionClass(REFLECTION_DOG)
                .getDefaultConstructorWithStringParameter()
                .invokeWithParameter("Little dog");

        var list = Stream.of(myClass.getMethods())
                .filter(method -> "sayHiTo".equals(method.getName()))
                .toList();

        System.out.println(list.get(0).invoke(instance, "Jorge"));

    }

    private static void exampleMethod() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        var myClass = Class.forName(REFLECTION_DOG);

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