package com.godeltech.bikesharing.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EquipmentTimeInUseRequest {
  private Long equipmentItemId;
  private Long minutesInUse;
}
