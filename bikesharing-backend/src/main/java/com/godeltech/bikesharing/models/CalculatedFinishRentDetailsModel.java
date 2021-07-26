package com.godeltech.bikesharing.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CalculatedFinishRentDetailsModel {
  private final long totalCost;
  private final long toBePaidAmount;
  private final long toBeRefundAmount;
}
