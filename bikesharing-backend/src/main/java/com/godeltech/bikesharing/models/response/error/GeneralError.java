package com.godeltech.bikesharing.models.response.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralError {

  private String exceptionCode;

  private String message;

}
