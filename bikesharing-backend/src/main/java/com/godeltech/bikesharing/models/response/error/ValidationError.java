package com.godeltech.bikesharing.models.response.error;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError extends GeneralError {
  public static final String EXCEPTION_CODE = "VALIDATION_EXCEPTION";

  private List<Violation> violations = null;
}
