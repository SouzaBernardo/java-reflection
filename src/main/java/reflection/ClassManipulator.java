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
            return new ConstructorManipulator(declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Error on get Default Constructor");
            return null;
        }
    }

    public ConstructorManipulator getDefaultConstructorWithStringParameter() {
        try {
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
            return new ConstructorManipulator(declaredConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("Error on get Constructor with String");
            return null;
        }
    }

    public Class<?> getReflectedClass() {
        return aClass;
    }
}
