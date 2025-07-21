package org.school.backend.application.exception;

public class ParentDataNotFoundException extends RuntimeException {
    public ParentDataNotFoundException() {
        super();
    }
  public ParentDataNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
    public ParentDataNotFoundException(String message) {
        super(message);
    }
  public ParentDataNotFoundException(final Throwable cause) {
    super(cause);
  }
}
