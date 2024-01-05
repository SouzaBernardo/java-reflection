package reflection;

import java.lang.reflect.Constructor;

public class ClassManipulator {
    private final Class<?> aClass;

    public ClassManipulator(Class<?> aClass) {
        this.aClass = aClass;
    }

    public ConstructorManipulator constructor() {
        try {
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            return new ConstructorManipulator(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Error on get Default Constructor");
            return null;
        }
    }

    public ConstructorManipulator constructor(Class<?> constructorParam) {
        try {
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(constructorParam);
            return new ConstructorManipulator(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
    }

    public ConstructorManipulator constructor(String constructorParam) {
        try {
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(constructorParam.getClass());
            return new ConstructorManipulator(aClass, declaredConstructor, constructorParam);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
    }

    public Class<?> getReflectedClass() {
        return aClass;
    }
}
