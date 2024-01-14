package reflection;

import reflection.application.service.PathService;
import reflection.core.useCase.impl.ReflectionUseCaseImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static final PathService pathService = new PathService();
    public static final String REFLECTION_DOG = "reflection.core.domain.Dog";
    public static final String METHOD_NAME = "sayHiTo";
    public static final ReflectionUseCaseImpl REFLECTION_IMPL_SERVICE = new ReflectionUseCaseImpl();

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        var userInput = scanner.nextLine();
        do {
            var pathResponse = pathService.validPath(userInput);
            var result = REFLECTION_IMPL_SERVICE.reflect(pathResponse.getFullClassName())
                    .constructor()
                    .useMethod()
                    .invoke(pathResponse);
            System.out.println(result);
            userInput = scanner.nextLine();
        } while (!userInput.equalsIgnoreCase("z"));

        scanner.close();

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
        var instance = REFLECTION_IMPL_SERVICE
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
        var myClass = REFLECTION_IMPL_SERVICE
                .reflect(REFLECTION_DOG)
                .getReflectedClass();

        var instance = REFLECTION_IMPL_SERVICE
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
        var invoke = REFLECTION_IMPL_SERVICE
                .reflect(REFLECTION_DOG)
                .constructor()
                .build();
        System.out.println(declaredMethod.invoke(invoke));
    }

    private static void exampleConstructorByBuilder() {
        var invoke = REFLECTION_IMPL_SERVICE
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
