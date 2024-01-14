package reflection.core.useCase.impl;


import reflection.core.exception.ReflectClassException;
import reflection.core.useCase.ReflectionUseCase;
import reflection.core.useCase.impl.ClassManipulatorUseCaseImpl;

public class ReflectionUseCaseImpl implements ReflectionUseCase {

    public ClassManipulatorUseCaseImpl reflect(String className) {
            return new ClassManipulatorUseCaseImpl(getReflectedClass(className));
    }

    public ClassManipulatorUseCaseImpl reflect(Class<?> aClass) {
            return new ClassManipulatorUseCaseImpl(aClass);
    }

    public Class<?> getReflectedClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            String errorMessage = "Error on Reflect class with name >> " + className;
            System.out.println(errorMessage);
            throw new ReflectClassException(errorMessage, e);
        }
    }
}
