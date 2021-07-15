package com.godeltech.bikesharing.models.response.error;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Error details")
public class GeneralError {

  private String exceptionCode;

  private String message;

  //private List<Violation> violations = null;
}
