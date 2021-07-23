package com.godeltech.bikesharing.service.calculator.impl;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.service.calculator.TotalCostCalculator;
import org.springframework.stereotype.Component;

@Component
public class TotalCostDayCalculator implements TotalCostCalculator {

  @Override
  public Long calculateTotalCost(RentCostModel rentCostModel, RentTimeModel rentTimeModel) {
//    TODO Probably the bug is here, for DAY rentCostModel.getDayPrice() should be here
    return rentCostModel.getHalfHourPrice() * rentTimeModel.getAmount();
  }

  @Override
  public RentTimeUnit getType() {
    return RentTimeUnit.DAY;
  }
}
