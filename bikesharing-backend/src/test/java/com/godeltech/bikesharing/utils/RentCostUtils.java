package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.persistence.entity.RentCost;

public class RentCostUtils {
  private static final Long HALF_HOUR_PRICE = 3L;
  private static final Long ONE_HOUR_PRICE = 4L;
  private static final Long DAY_PRICE = 15L;
  private static final Long MINIMAL_HOUR_PRICE = 1L;
  private static final Long HOUR_DISCOUNT = 1L;

  public static RentCost getRentCost() {
    var rentCost = new RentCost();
    rentCost.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroup());
    rentCost.setHalfHourPrice(HALF_HOUR_PRICE);
    rentCost.setOneHourPrice(ONE_HOUR_PRICE);
    rentCost.setDayPrice(DAY_PRICE);
    rentCost.setMinimalHourPrice(MINIMAL_HOUR_PRICE);
    rentCost.setHourDiscount(HOUR_DISCOUNT);
    return rentCost;
  }

  public static RentCostModel getRentCostModel(Long id) {
    var rentCostModel = new RentCostModel();
    rentCostModel.setId(id);
    rentCostModel.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroupModel());
    rentCostModel.setHalfHourPrice(HALF_HOUR_PRICE);
    rentCostModel.setOneHourPrice(ONE_HOUR_PRICE);
    rentCostModel.setDayPrice(DAY_PRICE);
    rentCostModel.setMinimalHourPrice(MINIMAL_HOUR_PRICE);
    rentCostModel.setHourDiscount(HOUR_DISCOUNT);
    return rentCostModel;
  }
}