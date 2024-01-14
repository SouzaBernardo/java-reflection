package reflection.adapters.Impl;


import reflection.adapters.ReflectionGateway;
import reflection.core.useCase.ClassManipulatorUseCase;
import reflection.core.useCase.ReflectionUseCase;
import reflection.core.useCase.impl.ClassManipulatorUseCaseImpl;

public class ReflectionGatewayImpl implements ReflectionGateway {

    private final ReflectionUseCase reflectionUseCase;

    ReflectionGatewayImpl(ReflectionUseCase useCase) {
        reflectionUseCase = useCase;
    }

    public ClassManipulatorUseCase reflect(String className) {
        return reflectionUseCase.reflect(className);
    }

    public ClassManipulatorUseCase reflect(Class<?> clazz) {
        return reflectionUseCase.reflect(clazz);
    }
}
