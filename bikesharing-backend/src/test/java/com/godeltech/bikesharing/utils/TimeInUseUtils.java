package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.EquipmentTimeInUseModel;
import com.godeltech.bikesharing.models.TimeInUseModel;
import java.time.LocalDate;

public class TimeInUseUtils {
  public static final Long MINUTES_IN_USE = 120L;
  public static final LocalDate DATE = LocalDate.of(2021, 1, 1);

  public static TimeInUseModel getTimeInUseModel(Long id) {
    var model = new TimeInUseModel();
    model.setId(id);
    model.setEquipmentItem(EquipmentItemUtils.getEquipmentItemModel(id));
    model.setMinutesInUse(MINUTES_IN_USE);
    model.setMaintenanceDate(DATE);
    return model;
  }

  public static EquipmentTimeInUseModel getEquipmentTimeInUseModel(Long id) {
    var model = new EquipmentTimeInUseModel();
    model.setEquipmentItemId(id);
    model.setMinutesInUse(MINUTES_IN_USE);
    return model;
  }
}
