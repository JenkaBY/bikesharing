package com.godeltech.bikesharing.exception;

public class ResourceStatusNotAppropriateException extends AbstractBusinessException {

  private static final long serialVersionUID = 800816183384847672L;

  public ResourceStatusNotAppropriateException(String message) {
    super(message);
  }
}
