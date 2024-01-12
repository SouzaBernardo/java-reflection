package reflection.core.service;

import reflection.core.useCase.ClassManipulatorUseCase;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

public class ClassManipulatorImpl implements ClassManipulatorUseCase {
    private final Class<?> aClass;

    public ClassManipulatorImpl(Class<?> aClass) {
        this.aClass = aClass;
    }

    public ConstructorManipulatorImpl constructor() {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor();
            return new ConstructorManipulatorImpl(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Error on get Default Constructor");
            return null;
        }
    }

    public ConstructorManipulatorImpl constructor(Class<?>... constructorParam) {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor(constructorParam);
            return new ConstructorManipulatorImpl(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
    }

    public ConstructorManipulatorImpl constructor(String constructorParam) {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor(constructorParam.getClass());
            return new ConstructorManipulatorImpl(aClass, declaredConstructor, constructorParam);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
    }

    public List<Field> declaredFields() {
        return Stream.of(aClass.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .toList();
    }

    public Class<?> getReflectedClass() {
        return aClass;
    }
}