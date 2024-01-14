package reflection.adapters;

import reflection.core.useCase.ConstructorManipulatorUseCase;

import java.lang.reflect.Field;
import java.util.List;

public interface ClassManipulatorGateway {
    ConstructorManipulatorUseCase constructor();
    ConstructorManipulatorUseCase constructor(Class<?>... constructorParam);
    List<Field> declaredFields();
    Class<?> getReflectedClass();
}
