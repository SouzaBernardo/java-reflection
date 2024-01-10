package reflection.partOne;

import reflection.partTwo.response.PathResponse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;

public class MethodManipulator {

    private BiFunction<String, Exception, Object> handleException;
    private final Constructor<?> constructor;
    private final String constructorParameter;
    private final List<Method> methods;

    public MethodManipulator(Method[] declaredMethods, String constructorParameter, Constructor<?> constructor) {
        this.methods = List.of(declaredMethods);
        this.constructorParameter = constructorParameter;
        this.constructor = constructor;
    }

    public MethodManipulator handleException(BiFunction<String, Exception, Object> handle) {
        this.handleException = handle;
        return this;
    }

    public Object invoke(String methodName, String parameter) {
        Method methodToInvoke = null;
        try {
            Object instance = getInstance();
            methodToInvoke = filterMethod(methodName);
            return methodToInvoke.invoke(instance, parameter);
        } catch (Exception e) {
            if (handleException != null)
                return handleException.apply(methodName, e);
            throw new RuntimeException(e);
        }
    }

    public Object invoke(String methodName) {
        Method methodToInvoke = null;
        try {
            Object instance = getInstance();
            methodToInvoke = filterMethod(methodName);
            return methodToInvoke.invoke(instance);
        } catch (Exception e) {
            if (handleException != null)
                return handleException.apply(methodName, e);
            throw new RuntimeException(e);
        }
    }

    public Object invoke(PathResponse pathResponse) {
        var method = pathResponse.getMethod();
        if (method.contains("get"))
            return invoke(method, pathResponse.getParameter());
        return invoke(method);
    }

    private Object getInstance() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if (this.constructorParameter.isEmpty())
            return constructor.newInstance();

        return constructor.newInstance(constructorParameter);
    }

    private Method filterMethod(String methodName) {
        return methods.stream()
                .filter(method -> method.getName().equals(methodName))
                .findFirst()
                .orElseThrow();
    }
}
