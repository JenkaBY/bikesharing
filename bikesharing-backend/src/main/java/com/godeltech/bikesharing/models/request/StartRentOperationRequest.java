package com.godeltech.bikesharing.models.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StartRentOperationRequest {
//  TODO move to separate enum TimeUnitCode{ long minutes; }
  public static final String HALF_HOUR_CODE = "HALF_HOUR";
  public static final String ONE_HOUR_CODE = "ONE_HOUR";
  public static final String DAY_CODE = "DAY";

  @NotBlank
  private String equipmentRegistrationNumber;

  @Pattern(regexp = "^(\\+\\d{1,3}( )?)?\\d{10,15}$",
      message = "Invalid phone number! Valid example:+1234...(to max 15 digits)")
  private String clientPhoneNumber;

  @Min(1)
  private Long deposit;
// TODO move to class RentTimeModel { TimeUnitCode code; Long amount }
  @NotBlank
  private String timeUnitCode;

  @Min(1)
  private Long timeUnitCount;
}
