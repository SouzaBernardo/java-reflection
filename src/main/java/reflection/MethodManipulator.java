package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class MethodManipulator {

    private BiFunction<Method, Exception, Object> handleException;
    private final Constructor<?> constructor;
    private final String parameter;
    private final List<Method> methods;

    public MethodManipulator(Class<?> aClass, Constructor<?> constructor) {
        this.constructor = constructor;
        this.parameter = "";
        this.methods = List.of(aClass.getDeclaredMethods());
    }

    public MethodManipulator(Class<?> aClass, Constructor<?> constructor, String parameter) {
        this.constructor = constructor;
        this.parameter = parameter;
        this.methods = List.of(aClass.getDeclaredMethods());
    }

    public MethodManipulator handleException(BiFunction<Method, Exception, Object> handle) {
        this.handleException = handle;
        return this;
    }

    public Object invoke(String methodName, String param) {
        Method methodToInvoke = null;
        try {
            Object instance;

            if (parameter.isEmpty())
                instance = constructor.newInstance();
            else
                instance = constructor.newInstance(parameter);

            methodToInvoke = methods.stream()
                    .filter(method -> method.getName().equals(methodName))
                    .findFirst()
                    .orElseThrow();

            return methodToInvoke.invoke(instance, param);
        } catch (Exception e) {
            if (handleException != null)
                handleException.apply(methodToInvoke, e);
            throw new RuntimeException(e);
        }
    }
    public Object invoke(String methodName) {
        Method methodToInvoke = null;
        try {
            Object instance;

            if (parameter.isEmpty())
                instance = constructor.newInstance();
            else
                instance = constructor.newInstance(parameter);

            methodToInvoke = methods.stream()
                    .filter(method -> method.getName().equals(methodName))
                    .findFirst()
                    .orElseThrow();

            return methodToInvoke.invoke(instance);
        } catch (Exception e) {
            if (handleException != null)
                handleException.apply(methodToInvoke, e);
            throw new RuntimeException(e);
        }
    }
}
