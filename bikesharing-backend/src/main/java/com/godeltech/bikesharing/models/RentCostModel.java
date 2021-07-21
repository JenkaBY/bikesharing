package com.godeltech.bikesharing.models;

import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentCostModel {
  private Long id;
  private EquipmentGroupModel equipmentGroup;
  private Long halfHourPrice;
  private Long oneHourPrice;
  private Long dayPrice;
  private Long minimalHourPrice;
  private Long hourDiscount;
}
