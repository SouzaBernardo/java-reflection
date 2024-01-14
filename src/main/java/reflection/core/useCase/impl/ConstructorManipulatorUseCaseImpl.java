package reflection.core.useCase.impl;

import reflection.core.useCase.ConstructorManipulatorUseCase;
import reflection.core.useCase.MethodManipulatorUseCase;
import reflection.core.useCase.impl.MethodManipulatorUseCaseImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorManipulatorUseCaseImpl implements ConstructorManipulatorUseCase {
    private final Class<?> aClass;
    private Constructor<?> constructor;
    private final String constructorParam;


    public ConstructorManipulatorUseCaseImpl(Class<?> aClass, Constructor<?> constructor) {
        this.aClass = aClass;
        this.constructor = constructor;
        this.constructorParam = "";
    }

    public ConstructorManipulatorUseCaseImpl(Class<?> aClass, Constructor<?> constructor, String constructorParam) {
        this.aClass = aClass;
        this.constructor = constructor;
        this.constructorParam = constructorParam;
    }

    public Object build() {
        try {
            return constructor.newInstance();
        } catch (Exception e) {
            System.out.println("Failure when build object");
            throw new RuntimeException(e);
        }
    }

    public Object build(Object... parameters) {
        try {
            return constructor.newInstance(parameters);
        } catch (Exception e) {
            try {
                this.constructor = aClass.getDeclaredConstructor(parameters.getClass());
                return constructor.newInstance(parameters);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public MethodManipulatorUseCase useMethod() {
        return new MethodManipulatorUseCaseImpl(aClass.getDeclaredMethods(), constructorParam, this);
    }
}
