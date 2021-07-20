package com.godeltech.bikesharing.models;

import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.lookup.TimePeriodModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentCostModel {
  private Long id;
  private EquipmentGroupModel equipmentGroup;
  private TimePeriodModel timePeriod;
  private Long cost;
}
