package com.godeltech.bikesharing.exception;

public class AbstractBusinessException extends RuntimeException {
  public AbstractBusinessException(final String message) {
    super(message);
  }
}
