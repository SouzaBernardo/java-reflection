package reflection.core.useCase;

public interface ReflectionUseCase {
    ClassManipulatorUseCase reflect(String className);
    ClassManipulatorUseCase reflect(Class<?> clazz);
}
