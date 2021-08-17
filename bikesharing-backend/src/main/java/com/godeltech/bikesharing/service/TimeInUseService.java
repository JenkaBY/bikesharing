package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.TimeInUseModel;
import java.time.LocalDate;

public interface TimeInUseService {

  void addTimeInUse(TimeInUseModel model);

  TimeInUseModel getOrCreateByEquipmentItemId(Long equipmentItemId);

  void setToZeroTime(Long timeInUseId, LocalDate maintenanceDate);
}
