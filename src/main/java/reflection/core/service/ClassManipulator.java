package reflection.core.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

public class ClassManipulator {
    private final Class<?> aClass;

    public ClassManipulator(Class<?> aClass) {
        this.aClass = aClass;
    }

    public ConstructorManipulator constructor() {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor();
            return new ConstructorManipulator(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Error on get Default Constructor");
            return null;
        }
    }

    public ConstructorManipulator constructor(Class<?> constructorParam) {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor(constructorParam);
            return new ConstructorManipulator(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
    }

    public ConstructorManipulator constructor(String constructorParam) {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor(constructorParam.getClass());
            return new ConstructorManipulator(aClass, declaredConstructor, constructorParam);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
    }

    public List<Field> declaredFields() {
        return List.of(aClass.getDeclaredFields());
    }

    public List<Field> declaredPrivateFields() {
        return Stream.of(aClass.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .toList();
    }

    public Class<?> getReflectedClass() {
        return aClass;
    }
}
