package com.godeltech.bikesharing.exception;

public class ResourceExistingPersistenceException extends AbstractBusinessException {

  private static final long serialVersionUID = 7482866541004536356L;

  public ResourceExistingPersistenceException(String message) {
    super(message);
  }
}
