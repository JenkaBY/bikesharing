package com.godeltech.bikesharing.models.enums;

public enum RentTimeUnit {
  HALF_HOUR(30),
  HOUR(60),
  DAY(24 * 60);

//  TODO make private and use lombok @Getter instead
  public final long durationInMin;
//TODO Remove private because enum constructor is private by default
  private RentTimeUnit(long durationInMin) {
    this.durationInMin = durationInMin;
  }
}
