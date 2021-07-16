package com.godeltech.bikesharing.exception;

public class ResourceNotFreeException extends AbstractBusinessException {
  private static final long serialVersionUID = 800816183384847672L;

  public ResourceNotFreeException(String message) {
    super(message);
  }
}