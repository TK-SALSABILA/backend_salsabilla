package org.school.backend.application.exception;

public class SubjectDataNotFoundException extends RuntimeException {
  public SubjectDataNotFoundException() {
    super();
  }
  public SubjectDataNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
  public SubjectDataNotFoundException(final String message) {
    super(message);
  }
  public SubjectDataNotFoundException(final Throwable cause) {
    super(cause);
  }
}
