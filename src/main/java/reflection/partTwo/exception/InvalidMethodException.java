package reflection.partTwo.exception;

public class InvalidMethodException extends RuntimeException {
    public InvalidMethodException() {
        super("Error with methodName null");
    }
}
