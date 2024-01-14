package reflection.core.useCase.impl;


import reflection.core.useCase.ReflectionUseCase;
import reflection.core.useCase.impl.ClassManipulatorUseCaseImpl;

public class ReflectionUseCaseImpl implements ReflectionUseCase {

    public ClassManipulatorUseCaseImpl reflect(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return new ClassManipulatorUseCaseImpl(aClass);
        } catch (ClassNotFoundException e) {
            String errorMessage = "Error on Reflect class with name >> " + className;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public ClassManipulatorUseCaseImpl reflect(Class<?> aClass) {
            return new ClassManipulatorUseCaseImpl(aClass);
    }
}
