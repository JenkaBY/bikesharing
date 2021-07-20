package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.lookup.TimePeriodModel;
import com.godeltech.bikesharing.persistence.entity.lookup.TimePeriod;

public class TimePeriodUtils {
  public static final String NAME = "NewPeriod";
  public static final String CODE = "HOUR";

  public static TimePeriod getTimePeriod() {
    var timePeriod = new TimePeriod();
    timePeriod.setName(NAME);
    timePeriod.setCode(CODE);
    return timePeriod;
  }

  public static TimePeriodModel getTimePeriodModel() {
    var timePeriodModel = new TimePeriodModel();
    timePeriodModel.setName(NAME);
    timePeriodModel.setCode(CODE);
    return timePeriodModel;
  }
}
