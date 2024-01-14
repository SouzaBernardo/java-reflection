package reflection.core.exception;

public class ReflectClassException extends RuntimeException {
    public ReflectClassException(String errorMessage, ClassNotFoundException exception) {
        super(errorMessage, exception);
    }
}
