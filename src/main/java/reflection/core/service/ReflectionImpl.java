package reflection.core.service;


import reflection.core.useCase.ReflectionUseCase;

public class ReflectionImpl implements ReflectionUseCase {

    public ClassManipulatorImpl reflect(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return new ClassManipulatorImpl(aClass);
        } catch (ClassNotFoundException e) {
            String errorMessage = "Error on Reflect class with name >> " + className;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public ClassManipulatorImpl reflect(Class<?> aClass) {
            return new ClassManipulatorImpl(aClass);
    }
}
