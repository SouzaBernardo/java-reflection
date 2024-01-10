package reflection.core.useCase;

import reflection.partTwo.response.PathResponse;

import java.util.function.BiFunction;

public interface MethodManipulatorUseCase {
    MethodManipulatorUseCase handleException(BiFunction<String, Exception, Object> handle);
    Object invoke(String methodName, Object... parameter);
    Object invoke(String methodName);
    Object invoke(PathResponse pathResponse);
}
