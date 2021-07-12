package com.godeltech.bikesharing.exception;

public class ResourceNotFoundException extends AbstractBusinessException {
  public ResourceNotFoundException(final String message) {
    super(message);
  }

  public ResourceNotFoundException(String type, String field, String value) {
    super(String.format("%s with %s: %s not found", type, field, value));
  }
}