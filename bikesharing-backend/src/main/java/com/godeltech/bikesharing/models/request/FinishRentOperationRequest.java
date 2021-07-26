package com.godeltech.bikesharing.models.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FinishRentOperationRequest {

  @NotBlank
  private String equipmentRegistrationNumber;

  @NotNull
  private LocalDateTime finishedAtTime;
}
