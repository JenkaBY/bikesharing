package com.godeltech.bikesharing.exception;

public class ResourceNotFreeException extends AbstractBusinessException {
  public ResourceNotFreeException(final String message) {
    super(message);
  }

}