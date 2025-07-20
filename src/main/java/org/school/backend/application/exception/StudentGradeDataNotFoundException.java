package org.school.backend.application.exception;

public class StudentGradeDataNotFoundException extends RuntimeException {
    public StudentGradeDataNotFoundException(String message) {
        super(message);
    }
    public  StudentGradeDataNotFoundException() {
        super();
    }
    public  StudentGradeDataNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
    public  StudentGradeDataNotFoundException(final Throwable cause) {
        super(cause);
    }

}
