package com.godeltech.bikesharing.exception;

public class RequestIdIsNotEqualToPathVariableException extends AbstractBusinessException {

  private static final long serialVersionUID = -6361842343555709231L;

  public RequestIdIsNotEqualToPathVariableException(String message) {
    super(message);
  }
}
