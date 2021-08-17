package com.godeltech.bikesharing.models;

import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TimeInUseModel {
  private Long id;
  private long minutesInUse;
  private LocalDate maintenanceDate;
  private EquipmentItemModel equipmentItem;
}
