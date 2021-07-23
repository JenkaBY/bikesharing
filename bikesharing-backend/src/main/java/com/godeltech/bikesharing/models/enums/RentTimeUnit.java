package com.godeltech.bikesharing.models.enums;

public enum RentTimeUnit {
  HALF_HOUR(30),
  HOUR(60),
  DAY(24 * 60);

  public final long durationInMin;

  private RentTimeUnit(long durationInMin) {
    this.durationInMin = durationInMin;
  }
}
