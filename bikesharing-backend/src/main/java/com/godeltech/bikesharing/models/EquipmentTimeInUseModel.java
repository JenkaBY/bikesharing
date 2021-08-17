package com.godeltech.bikesharing.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentTimeInUseModel {
  private Long equipmentItemId;
  private Long minutesInUse;
}
