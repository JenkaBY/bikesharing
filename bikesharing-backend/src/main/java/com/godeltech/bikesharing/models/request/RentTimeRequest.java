package com.godeltech.bikesharing.models.request;

import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentTimeRequest {
  private RentTimeUnit rentTimeUnit;

  @Min(1)
  private Long amount;
}
