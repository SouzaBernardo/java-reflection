package reflection.core.exception;

public class InvalidClassNameException extends RuntimeException {
    public InvalidClassNameException(String className) {
        super("Error with className: " + className);
    }
}
