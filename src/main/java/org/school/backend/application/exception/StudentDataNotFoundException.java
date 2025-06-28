package org.school.backend.application.exception;

public class StudentDataNotFoundException extends RuntimeException {
    public StudentDataNotFoundException() {
        super();
    }
    public StudentDataNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
    public StudentDataNotFoundException(final String message) {
        super(message);
    }
    public StudentDataNotFoundException(final Throwable cause) {
        super(cause);
    }
}
