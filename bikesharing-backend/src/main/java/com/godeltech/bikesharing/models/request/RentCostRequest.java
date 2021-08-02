package com.godeltech.bikesharing.models.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentCostRequest {
  @NotBlank
  private String equipmentGroupCode;
  @NotNull
  @Min(0)
  private Long halfHourPrice;
  @NotNull
  @Min(0)
  private Long oneHourPrice;
  @NotNull
  @Min(0)
  private Long dayPrice;
  @NotNull
  @Min(0)
  private Long minimalHourPrice;
  @NotNull
  @Min(0)
  private Long hourDiscount;
}