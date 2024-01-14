package reflection.core.useCase.impl;

import reflection.core.useCase.ConstructorManipulatorUseCase;
import reflection.core.useCase.MethodManipulatorUseCase;
import reflection.core.response.PathResponse;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;

public class MethodManipulatorUseCaseImpl implements MethodManipulatorUseCase {

    private BiFunction<String, Exception, Object> handleException;
    private final ConstructorManipulatorUseCase constructorManipulator;
    private final String constructorParameter;
    private final List<Method> methods;

    public MethodManipulatorUseCaseImpl(Method[] declaredMethods,
                                        String constructorParameter,
                                        ConstructorManipulatorUseCase constructorManipulator) {

        this.methods = List.of(declaredMethods);
        this.constructorParameter = constructorParameter;
        this.constructorManipulator = constructorManipulator;
    }

    public MethodManipulatorUseCaseImpl handleException(BiFunction<String, Exception, Object> handle) {
        this.handleException = handle;
        return this;
    }

    public Object invoke(String methodName, Object... parameter) {
        try {
            Object instance = getInstance();
            var methodToInvoke = filterMethod(methodName);
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

    private Object getInstance() {
        if (this.constructorParameter.isEmpty())
            return constructorManipulator.build();

        return constructorManipulator.build(constructorParameter);
    }

    private Method filterMethod(String methodName) {
        return methods.stream()
                .filter(method -> method.getName().equals(methodName))
                .findFirst()
                .orElseThrow();
    }
}
