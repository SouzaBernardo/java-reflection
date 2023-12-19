package reflection;

import java.lang.reflect.Constructor;

public class ConstructorManipulator {
    private final Constructor<?> constructor;

    public ConstructorManipulator(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public Object invoke() {
        try {
            return constructor.newInstance();
        } catch (Exception e) {
            System.out.println("Error on constructor");
            return null;
        }
    }
}
