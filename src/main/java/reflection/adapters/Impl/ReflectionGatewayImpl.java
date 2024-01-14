package reflection.adapters.Impl;


import reflection.adapters.ReflectionGateway;
import reflection.core.useCase.ClassManipulatorUseCase;
import reflection.core.useCase.ReflectionUseCase;
import reflection.core.useCase.impl.ClassManipulatorUseCaseImpl;
import reflection.core.useCase.impl.ReflectionUseCaseImpl;

public class ReflectionGatewayImpl implements ReflectionGateway {

    private final ReflectionUseCase reflectionUseCase;

    public ReflectionGatewayImpl() {
        reflectionUseCase = new ReflectionUseCaseImpl();
    }

    public ClassManipulatorUseCase reflect(String className) {
        return reflectionUseCase.reflect(className);
    }

    public ClassManipulatorUseCase reflect(Class<?> clazz) {
        return reflectionUseCase.reflect(clazz);
    }

    public Class<?> getReflectedClass(String className) {
        return reflectionUseCase.getReflectedClass(className);
    }
}
