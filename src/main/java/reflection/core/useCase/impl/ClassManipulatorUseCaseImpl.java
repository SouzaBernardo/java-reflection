package reflection.core.useCase.impl;

import reflection.core.useCase.ClassManipulatorUseCase;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

public class ClassManipulatorUseCaseImpl implements ClassManipulatorUseCase {
    private final Class<?> aClass;

    public ClassManipulatorUseCaseImpl(Class<?> aClass) {
        this.aClass = aClass;
    }

    public ConstructorManipulatorUseCaseImpl constructor() {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor();
            return new ConstructorManipulatorUseCaseImpl(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Error on get Default Constructor");
            return null;
        }
    }

    public ConstructorManipulatorUseCaseImpl constructor(Class<?>... constructorParam) {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor(constructorParam);
            return new ConstructorManipulatorUseCaseImpl(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
    }

    public ConstructorManipulatorUseCaseImpl constructor(String constructorParam) {
        try {
            var declaredConstructor = aClass.getDeclaredConstructor(constructorParam.getClass());
            return new ConstructorManipulatorUseCaseImpl(aClass, declaredConstructor, constructorParam);
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
