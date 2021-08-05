package com.godeltech.bikesharing.models.response.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GeneralError {

  private final String exceptionCode;
  private final String message;
}
