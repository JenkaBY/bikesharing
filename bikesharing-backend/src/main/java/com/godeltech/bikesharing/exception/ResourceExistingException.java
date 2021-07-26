package com.godeltech.bikesharing.exception;

public class ResourceExistingException extends AbstractBusinessException {
  private static final long serialVersionUID = 7482866541004536356L;

  public ResourceExistingException(String message) {
    super(message);
  }
}
