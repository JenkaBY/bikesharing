package com.godeltech.bikesharing.service.util;

import java.time.LocalDateTime;

public class FinishTimeCalculator {
  public static LocalDateTime calculateFinishTime(LocalDateTime startTime,Long deposit){
    return startTime.plusHours(1);
  }
}
