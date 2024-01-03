package reflection;

import java.lang.reflect.Constructor;

public class ConstructorManipulator {
    private final Class<?> aClass;
    private final Constructor<?> constructor;

    public ConstructorManipulator(Class<?> aClass, Constructor<?> constructor) {
        this.aClass = aClass;
        this.constructor = constructor;
    }

    public MethodManipulator invoke() {
        return new MethodManipulator(aClass, constructor);
    }

    public MethodManipulator invokeWithParameter(String parameter) {
        return new MethodManipulator(aClass, constructor, parameter);
    }
}
