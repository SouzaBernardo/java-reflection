package reflection.core.useCase;

public interface ConstructorManipulatorUseCase {
    Object build();
    <T> Object build(T parameter);
    MethodManipulatorUseCase useMethod();
}
