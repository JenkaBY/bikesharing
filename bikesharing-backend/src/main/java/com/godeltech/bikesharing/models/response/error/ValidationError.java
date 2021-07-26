package com.godeltech.bikesharing.models.response.error;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError extends GeneralError {
  private List<Violation> violations = null;

  public ValidationError(String exceptionCode, String message) {
    super(exceptionCode, message);
  }
}
