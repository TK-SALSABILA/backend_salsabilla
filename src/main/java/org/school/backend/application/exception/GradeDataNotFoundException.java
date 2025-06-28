package org.school.backend.application.exception;

public class GradeDataNotFoundException extends RuntimeException {
  public GradeDataNotFoundException() {
    super();
  }
  public GradeDataNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
  public GradeDataNotFoundException(final String message) {
    super(message);
  }
  public GradeDataNotFoundException(final Throwable cause) {
    super(cause);
  }
}
