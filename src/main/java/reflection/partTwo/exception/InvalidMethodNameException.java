package reflection.partTwo.exception;

public class InvalidMethodNameException extends RuntimeException {
    public InvalidMethodNameException(String methodName) {
        super("Error with methodName: " + methodName);
    }
}
