package com.godeltech.bikesharing.models.enums;

import java.time.Duration;
import lombok.Getter;

@Getter
public enum RentTimeUnit {
  HALF_HOUR(Duration.ofMinutes(30)),
  HOUR(Duration.ofHours(1)),
  DAY(Duration.ofDays(1));

  private final Duration duration;

  RentTimeUnit(Duration duration) {
    this.duration = duration;
  }
}
