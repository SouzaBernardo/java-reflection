package reflection.core.useCase;

public interface ConstructorManipulatorUseCase {
    Object build();
    Object build(Object... parameters);
    MethodManipulatorUseCase useMethod();
}
