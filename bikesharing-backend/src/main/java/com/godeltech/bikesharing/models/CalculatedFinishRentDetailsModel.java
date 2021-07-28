package com.godeltech.bikesharing.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CalculatedFinishRentDetailsModel {
  private final long totalCost;
  private final long toBePaidAmount;
  private final long toBeRefundAmount;
}
