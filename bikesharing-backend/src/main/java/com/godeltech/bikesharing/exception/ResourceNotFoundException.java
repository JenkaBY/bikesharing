package com.godeltech.bikesharing.exception;

public class ResourceNotFoundException extends AbstractBusinessException {
  private static final long serialVersionUID = 8067023990712655807L;

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String type, String field, String value) {
    super(String.format("%s with %s: %s not found", type, field, value));
  }

  public ResourceNotFoundException(String type, String field, Long value) {
    super(String.format("%s with %s: %s not found", type, field, value));
  }
}