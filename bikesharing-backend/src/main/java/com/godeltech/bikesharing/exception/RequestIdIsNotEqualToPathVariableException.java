package com.godeltech.bikesharing.exception;

//TODO remove it because it's redundant
public class RequestIdIsNotEqualToPathVariableException extends AbstractBusinessException {

  private static final long serialVersionUID = -6361842343555709231L;

  public RequestIdIsNotEqualToPathVariableException(String message) {
    super(message);
  }
}
