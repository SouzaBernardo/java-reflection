package reflection.adapters;

import reflection.core.useCase.ClassManipulatorUseCase;

public interface ReflectionGateway {
    ClassManipulatorUseCase reflect(String className);
    ClassManipulatorUseCase reflect(Class<?> clazz);
}
