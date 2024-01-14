package reflection.adapters;

import reflection.core.useCase.ConstructorManipulatorUseCase;
import reflection.core.useCase.MethodManipulatorUseCase;

import java.lang.reflect.Field;
import java.util.List;

public interface ConstructorManipulatorGateway {
    Object build();
    Object build(Object... parameters);
    MethodManipulatorUseCase useMethod();
}
