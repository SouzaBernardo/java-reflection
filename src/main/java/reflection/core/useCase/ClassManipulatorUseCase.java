package reflection.core.useCase;

import java.lang.reflect.Field;
import java.util.List;

public interface ClassManipulatorUseCase {
    ConstructorManipulatorUseCase constructor();
    ConstructorManipulatorUseCase constructor(Class<?>... constructorParam);
    List<Field> declaredFields();
    Class<?> getReflectedClass();
}
