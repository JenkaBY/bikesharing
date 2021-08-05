package com.godeltech.bikesharing.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CalculatedRentDetailsModel {

  private final long totalCost;
  private final long paidMinutes;
}
