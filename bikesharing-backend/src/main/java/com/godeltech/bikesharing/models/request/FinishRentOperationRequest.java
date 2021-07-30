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
//TODO Request must not contain entity id. The id should be retrieved from path variable in controller
  @NotNull
  private Long id;

  @NotNull
  private LocalDateTime finishedAtTime;
//TODO the name doesn't reflect that it's a money. I'd add amount at the end
  private Long fines;
  //TODO Remove 's' at the end. It's a singular comment
  private String comments;
}
