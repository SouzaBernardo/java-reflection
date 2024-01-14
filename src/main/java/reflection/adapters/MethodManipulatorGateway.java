package reflection.adapters;

import reflection.core.response.PathResponse;
import reflection.core.useCase.MethodManipulatorUseCase;

import java.util.function.BiFunction;

public interface MethodManipulatorGateway {
    MethodManipulatorUseCase handleException(BiFunction<String, Exception, Object> handle);
    Object invoke(String methodName, Object... parameter);
    Object invoke(String methodName);
    Object invoke(PathResponse pathResponse);
}
