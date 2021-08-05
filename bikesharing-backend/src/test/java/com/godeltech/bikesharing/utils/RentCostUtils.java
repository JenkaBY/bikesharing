package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.request.RentCostRequest;
import com.godeltech.bikesharing.models.response.RentCostResponse;
import com.godeltech.bikesharing.persistence.entity.RentCost;

public class RentCostUtils {
  public static final Long HALF_HOUR_PRICE = 3L;
  public static final Long ONE_HOUR_PRICE = 4L;
  public static final Long DAY_PRICE = 15L;
  public static final Long MINIMAL_HOUR_PRICE = 1L;
  public static final Long HOUR_DISCOUNT = 1L;

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

  public static RentCostRequest getRentCostRequest() {
    var rentCost = new RentCostRequest();
    rentCost.setEquipmentGroupCode(EquipmentGroupUtils.CODE);
    rentCost.setHalfHourPrice(HALF_HOUR_PRICE);
    rentCost.setOneHourPrice(ONE_HOUR_PRICE);
    rentCost.setDayPrice(DAY_PRICE);
    rentCost.setMinimalHourPrice(MINIMAL_HOUR_PRICE);
    rentCost.setHourDiscount(HOUR_DISCOUNT);
    return rentCost;
  }

  public static RentCostResponse getRentCostResponse(Long id) {
    var rentCost = new RentCostResponse();
    rentCost.setId(id);
    rentCost.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroupResponse(id));
    rentCost.setHalfHourPrice(HALF_HOUR_PRICE);
    rentCost.setOneHourPrice(ONE_HOUR_PRICE);
    rentCost.setDayPrice(DAY_PRICE);
    rentCost.setMinimalHourPrice(MINIMAL_HOUR_PRICE);
    rentCost.setHourDiscount(HOUR_DISCOUNT);
    return rentCost;
  }
}
