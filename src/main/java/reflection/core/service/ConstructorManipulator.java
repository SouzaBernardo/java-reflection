package reflection.core.service;

import reflection.core.service.MethodManipulator;

import java.lang.reflect.Constructor;

public class ConstructorManipulator {
    private final Class<?> aClass;
    private final Constructor<?> constructor;
    private final String constructorParam;


    public ConstructorManipulator(Class<?> aClass, Constructor<?> constructor) {
        this.aClass = aClass;
        this.constructor = constructor;
        this.constructorParam = "";
    }

    public ConstructorManipulator(Class<?> aClass, Constructor<?> constructor, String constructorParam) {
        this.aClass = aClass;
        this.constructor = constructor;
        this.constructorParam = constructorParam;
    }

    public Object build() {
        try {
            return aClass.getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {
            System.out.println("Failure when build object");
            throw new RuntimeException(e);
        }
    }

    public <T> Object build(T parameter) {
        try {
            return aClass.getDeclaredConstructor(parameter.getClass())
                    .newInstance(parameter);
        } catch (Exception e) {
            System.out.println("Failure when build object");
            throw new RuntimeException(e);
        }
    }

    public MethodManipulator useMethod() {
        return new MethodManipulator(aClass.getDeclaredMethods(), constructorParam, constructor);
    }
}
