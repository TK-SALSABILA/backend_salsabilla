package org.school.backend.application.exception;

public class TuitionFeeDataNotFound extends RuntimeException {
    public TuitionFeeDataNotFound(String message) {
        super(message);
    }
    public TuitionFeeDataNotFound() {
        super();
    }
    public TuitionFeeDataNotFound(final String message, final Throwable cause) {
        super(message, cause);
    }
    public TuitionFeeDataNotFound(final Throwable cause) {
        super(cause);
    }
}
