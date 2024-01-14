package reflection.adapters.Impl;

import reflection.adapters.ClassManipulatorGateway;
import reflection.adapters.ConstructorManipulatorGateway;
import reflection.core.useCase.ConstructorManipulatorUseCase;
import reflection.core.useCase.MethodManipulatorUseCase;
import reflection.core.useCase.impl.ConstructorManipulatorUseCaseImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class ConstructorManipulatorGatewayImpl implements ConstructorManipulatorGateway {

    private final ConstructorManipulatorUseCase constructorManipulator;

    ConstructorManipulatorGatewayImpl(ConstructorManipulatorUseCase useCase) {
        constructorManipulator = useCase;
    }

    public Object build() {
        return constructorManipulator.build();
    }

    public Object build(Object... parameters) {
        return constructorManipulator.build(parameters);
    }

    public MethodManipulatorUseCase useMethod() {
        return constructorManipulator.useMethod();
    }
}
