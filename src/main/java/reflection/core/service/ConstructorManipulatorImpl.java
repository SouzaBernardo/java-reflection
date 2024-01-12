package reflection.core.service;

import reflection.core.useCase.ConstructorManipulatorUseCase;
import reflection.core.useCase.MethodManipulatorUseCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorManipulatorImpl implements ConstructorManipulatorUseCase {
    private final Class<?> aClass;
    private Constructor<?> constructor;
    private final String constructorParam;


    public ConstructorManipulatorImpl(Class<?> aClass, Constructor<?> constructor) {
        this.aClass = aClass;
        this.constructor = constructor;
        this.constructorParam = "";
    }

    public ConstructorManipulatorImpl(Class<?> aClass, Constructor<?> constructor, String constructorParam) {
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
        return new MethodManipulatorImpl(aClass.getDeclaredMethods(), constructorParam, this);
    }
}
