package reflection;

import java.lang.reflect.Constructor;

public class Reflection {

    private String className;

    private Constructor<?> constructor;

    public Reflection reflectionClass(String className) {
        return this;
    }

    public Reflection getConstructor() {
        return this;
    }

    public Object invoke() {
        try {
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
