package com.godeltech.bikesharing.models.response.error;

public class InternalServerError extends GeneralError {

  public InternalServerError(String exceptionCode, String message) {
    super(exceptionCode, message);
  }
}

