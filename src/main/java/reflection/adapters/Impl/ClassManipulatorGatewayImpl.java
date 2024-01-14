package reflection.adapters.Impl;

import reflection.adapters.ClassManipulatorGateway;
import reflection.core.useCase.ClassManipulatorUseCase;
import reflection.core.useCase.ConstructorManipulatorUseCase;
import reflection.core.useCase.impl.ClassManipulatorUseCaseImpl;

import java.lang.reflect.Field;
import java.util.List;

public class ClassManipulatorGatewayImpl implements ClassManipulatorGateway {

    private final ClassManipulatorUseCase classManipulator;

    ClassManipulatorGatewayImpl(ClassManipulatorUseCase useCase) {
        classManipulator = useCase;
    }

    public ConstructorManipulatorUseCase constructor() {
        return classManipulator.constructor();
    }

    public ConstructorManipulatorUseCase constructor(Class<?>... constructorParam) {
        return classManipulator.constructor(constructorParam);
    }

    public List<Field> declaredFields() {
        return classManipulator.declaredFields();
    }

    public Class<?> getReflectedClass() {
        return classManipulator.getReflectedClass();
    }
}
