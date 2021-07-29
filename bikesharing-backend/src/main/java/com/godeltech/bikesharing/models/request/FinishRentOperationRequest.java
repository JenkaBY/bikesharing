package com.godeltech.bikesharing.models.request;

import com.godeltech.bikesharing.support.ValidNotNullComments;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ValidNotNullComments
public class FinishRentOperationRequest {

  @NotNull
  private Long id;

  @NotNull
  private LocalDateTime finishedAtTime;

  private Long fines;

  private String comments;
}
