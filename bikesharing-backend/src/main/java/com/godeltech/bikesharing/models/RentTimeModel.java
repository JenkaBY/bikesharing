package com.godeltech.bikesharing.models;

import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentTimeModel {
  private RentTimeUnit rentTimeUnit;
  private Long amount;
}
