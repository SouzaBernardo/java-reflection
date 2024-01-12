package reflection.core.useCase;

import reflection.core.service.ConstructorManipulator;

import java.lang.reflect.Field;
import java.util.List;

public interface ClassManipulatorUseCase {
    ConstructorManipulator constructor();
    ConstructorManipulator constructor(Class<?>... constructorParam);
    List<Field> declaredFields();
    Class<?> getReflectedClass();
}
