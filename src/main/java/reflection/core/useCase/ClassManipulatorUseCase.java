package reflection.core.useCase;

import reflection.core.service.ConstructorManipulatorImpl;

import java.lang.reflect.Field;
import java.util.List;

public interface ClassManipulatorUseCase {
    ConstructorManipulatorImpl constructor();
    ConstructorManipulatorImpl constructor(Class<?>... constructorParam);
    List<Field> declaredFields();
    Class<?> getReflectedClass();
}
