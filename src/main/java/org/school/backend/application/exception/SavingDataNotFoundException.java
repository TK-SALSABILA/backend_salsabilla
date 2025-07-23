package org.school.backend.application.exception;

public class SavingDataNotFoundException extends RuntimeException {
  public SavingDataNotFoundException() {
    super();
  }
  public SavingDataNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
  public SavingDataNotFoundException(final String message) {
    super(message);
  }
  public SavingDataNotFoundException(final Throwable cause) {
    super(cause);
  }
}
