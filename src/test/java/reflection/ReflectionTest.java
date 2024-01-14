package reflection;

import org.junit.jupiter.api.Test;
import reflection.core.useCase.ReflectionUseCase;
import reflection.core.useCase.impl.ReflectionUseCaseImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static reflection.Main.METHOD_NAME;
import static reflection.Main.REFLECTION_DOG;

public class ReflectionTest {

    private final ReflectionUseCase reflectionUseCase = new ReflectionUseCaseImpl();

    @Test
    void exampleInvokeWithMethodManipulator() {
        var instance = reflectionUseCase
                .reflect(REFLECTION_DOG)
                .constructor("Little Dog")
                .useMethod()
                .handleException((method, error) -> {
                    System.out.println("Error on method: " + error.getMessage());
                    throw new RuntimeException("Error on method: " + method);
                })
                .invoke(METHOD_NAME, "Vii");
        System.out.println(instance);
    }

    @Test
    void exampleMethodWithParameters() throws InvocationTargetException, IllegalAccessException {
        var myClass = reflectionUseCase
                .getReflectedClass(REFLECTION_DOG);

        var instance = reflectionUseCase
                .reflect(REFLECTION_DOG)
                .constructor(String.class)
                .build("Little Dog");

        var list = Stream.of(myClass.getMethods())
                .filter(method -> METHOD_NAME.equals(method.getName()))
                .toList();

        System.out.println(list.get(0).invoke(instance, "Jorge"));

    }

    @Test
    void exampleMethod() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        var myClass = Class.forName(REFLECTION_DOG);

        var declaredMethod = myClass.getDeclaredMethods()[0];
        var invoke = reflectionUseCase
                .reflect(REFLECTION_DOG)
                .constructor()
                .build();
        System.out.println(declaredMethod.invoke(invoke));
    }

    private static void exampleConstructorByBuilder(ReflectionUseCase reflectionUseCase) {
        var invoke = reflectionUseCase
                .reflect(REFLECTION_DOG)
                .constructor()
                .build();
        System.out.println(invoke);
    }
    @Test
    void exampleGetDeclaredConstructors() throws ClassNotFoundException,
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
