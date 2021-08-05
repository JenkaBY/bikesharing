package com.godeltech.bikesharing.models.request;

import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.support.ValidRentTimeRequestAmount;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ValidRentTimeRequestAmount
public class RentTimeRequest {

  private RentTimeUnit rentTimeUnit;

  private Long amount;
}
