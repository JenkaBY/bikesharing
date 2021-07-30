package com.godeltech.bikesharing.models.request;

import com.godeltech.bikesharing.support.ValidFinesAndComments;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ValidFinesAndComments
public class FinishRentOperationRequest {

  @NotNull
  private LocalDateTime finishedAtTime;

  private Long finesAmount;

  private String comment;
}
