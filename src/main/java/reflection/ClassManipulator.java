package reflection;

import java.lang.reflect.Constructor;

public class ClassManipulator {
    private final Class<?> aClass;

    public ClassManipulator(Class<?> aClass) {
        this.aClass = aClass;
    }

    public ConstructorManipulator getDefaultConstructor() {
        try {
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            return new ConstructorManipulator(aClass, declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Error on get Default Constructor");
            return null;
        }
    }

    public ConstructorManipulator getDefaultConstructorWithStringParameter(){
        Constructor<?> declaredConstructor;
        try {
            declaredConstructor = aClass.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor not found");
            throw new RuntimeException(e);
        }
        return new ConstructorManipulator(aClass, declaredConstructor);
    }

    public Class<?> getReflectedClass() {
        return aClass;
    }
}
