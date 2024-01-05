package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;

public class MethodManipulator {

    private BiFunction<Method, Exception, Object> handleException;
    private final Constructor<?> constructor;
    private final String constructorParameter;
    private final List<Method> methods;

    public MethodManipulator(Method[] declaredMethods, String methodName, Constructor<?> constructor) {
        this.methods = List.of(declaredMethods);
        this.constructorParameter = methodName;
        this.constructor = constructor;
    }

    public MethodManipulator handleException(BiFunction<Method, Exception, Object> handle) {
        this.handleException = handle;
        return this;
    }

    public Object invokeMethod(String parameter) {
        Method methodToInvoke = null;
        try {
            Object instance;

            if (this.constructorParameter.isEmpty())
                instance = constructor.newInstance();
            else
                instance = constructor.newInstance(this.constructorParameter);

            methodToInvoke = methods.stream()
                    .filter(method -> method.getName().equals(constructorParameter))
                    .findFirst()
                    .orElseThrow();

            return methodToInvoke.invoke(instance, parameter);
        } catch (Exception e) {
            if (handleException != null)
                handleException.apply(methodToInvoke, e);
            throw new RuntimeException(e);
        }
    }
}
