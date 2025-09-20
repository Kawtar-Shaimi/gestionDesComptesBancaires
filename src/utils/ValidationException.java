package utils;

public class ValidationException extends RuntimeException {  // <-- changer Exception → RuntimeException

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }
}
