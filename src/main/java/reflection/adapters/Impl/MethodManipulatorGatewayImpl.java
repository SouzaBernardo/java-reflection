package reflection.adapters.Impl;

import reflection.adapters.MethodManipulatorGateway;
import reflection.core.response.PathResponse;
import reflection.core.useCase.ConstructorManipulatorUseCase;
import reflection.core.useCase.MethodManipulatorUseCase;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;

public class MethodManipulatorGatewayImpl implements MethodManipulatorGateway {

    private final MethodManipulatorUseCase methodManipulator;

    MethodManipulatorGatewayImpl(MethodManipulatorUseCase useCase) {
        methodManipulator = useCase;
    }

    public MethodManipulatorUseCase handleException(BiFunction<String, Exception, Object> handle) {
        return methodManipulator.handleException(handle);
    }

    public Object invoke(String methodName, Object... parameter) {
        return methodManipulator.invoke(methodName, parameter);
    }

    public Object invoke(String methodName) {
        return methodManipulator.invoke(methodName);
    }

    public Object invoke(PathResponse pathResponse) {
        return methodManipulator.invoke(pathResponse);
    }
}
