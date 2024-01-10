package reflection.core.exception;

public class InvalidPathException extends RuntimeException {
    public InvalidPathException(String message) {
        super("Error with path: " + message );
    }
}
