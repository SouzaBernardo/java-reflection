package reflection.partTwo.exception;

public class InvalidClassNameException extends RuntimeException {
    public InvalidClassNameException(String className) {
        super("Error with className: " + className);
    }
}
