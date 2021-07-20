package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.persistence.entity.RentCost;

public class RentCostUtils {
  public static final Long COST = 10L;

  public static RentCost getRentCost() {
    var rentCost = new RentCost();
    rentCost.setCost(COST);
    rentCost.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroup());
    rentCost.setTimePeriod(TimePeriodUtils.getTimePeriod());
    return rentCost;
  }

  public static RentCostModel getRentCostModel(Long id) {
    var rentCostModel = new RentCostModel();
    rentCostModel.setId(id);
    rentCostModel.setCost(COST);
    rentCostModel.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroupModel());
    rentCostModel.setTimePeriod(TimePeriodUtils.getTimePeriodModel());
    return rentCostModel;
  }
}
