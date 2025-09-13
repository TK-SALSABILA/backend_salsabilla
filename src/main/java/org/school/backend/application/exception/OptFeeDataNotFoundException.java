package org.school.backend.application.exception;

public class OptFeeDataNotFoundException extends RuntimeException {
  public OptFeeDataNotFoundException() {
    super();
  }
  public OptFeeDataNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
  public OptFeeDataNotFoundException(final String message) {
    super(message);
  }
  public OptFeeDataNotFoundException(final Throwable cause) {
    super(cause);
  }
}
