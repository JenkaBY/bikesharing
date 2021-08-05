package com.godeltech.bikesharing.models.response;

import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentCostResponse {

  private Long id;
  private EquipmentGroupResponse equipmentGroup;
  private Long halfHourPrice;
  private Long oneHourPrice;
  private Long dayPrice;
  private Long minimalHourPrice;
  private Long hourDiscount;
}
