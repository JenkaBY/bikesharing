package com.godeltech.bikesharing.models.enums;

import lombok.Getter;

@Getter
public enum RentTimeUnit {
  HALF_HOUR(30),
  HOUR(60),
  DAY(24 * 60);
//TODO Let's try to use Duration class in this field. I think it will be more readable, understandable and flexible
  private final long durationInMin;

  RentTimeUnit(long durationInMin) {
    this.durationInMin = durationInMin;
  }
}
