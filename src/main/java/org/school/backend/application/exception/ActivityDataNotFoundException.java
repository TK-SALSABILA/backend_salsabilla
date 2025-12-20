package org.school.backend.application.exception;

public class ActivityDataNotFoundException extends RuntimeException {
    public ActivityDataNotFoundException() {
        super();
    }
    public ActivityDataNotFoundException(String message) {
        super(message);
    }
    public ActivityDataNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
    public ActivityDataNotFoundException(final Throwable cause) {
        super(cause);
    }
}
