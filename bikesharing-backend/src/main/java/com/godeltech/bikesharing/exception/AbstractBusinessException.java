package com.godeltech.bikesharing.exception;

public class AbstractBusinessException extends RuntimeException {

  private static final long serialVersionUID = 8305472197491636438L;

  public AbstractBusinessException(String message) {
    super(message);
  }
}
