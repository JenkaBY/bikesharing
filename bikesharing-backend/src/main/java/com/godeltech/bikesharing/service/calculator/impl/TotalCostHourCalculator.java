package com.godeltech.bikesharing.service.calculator.impl;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.service.calculator.TotalCostCalculator;
import org.springframework.stereotype.Component;

@Component
public class TotalCostHourCalculator implements TotalCostCalculator {

  @Override
  public Long calculateTotalCost(RentCostModel rentCostModel, RentTimeModel rentTimeModel) {
    var totalCost = rentCostModel.getOneHourPrice();
    var currentHourPrice = rentCostModel.getOneHourPrice();
    var timeUnitAmount = rentTimeModel.getAmount();
//    TODO for future improvements: These calculations are the same for each equipment Group, I think we would cache them.
    while (timeUnitAmount > 1) {
      currentHourPrice = changeCurrentHourPrice(currentHourPrice, rentCostModel.getHourDiscount(),
          rentCostModel.getMinimalHourPrice());
      totalCost += currentHourPrice;
      timeUnitAmount -= 1;
    }
    return totalCost;
  }
//TODO Rename to calculateHourPrice
  private Long changeCurrentHourPrice(Long currentHourPrice, Long hourDiscount, Long minimalHourPrice) {
    if (currentHourPrice > minimalHourPrice) {
      return currentHourPrice - hourDiscount;
    } else {
      return currentHourPrice;
    }
  }

  @Override
  public RentTimeUnit getType() {
    return RentTimeUnit.HOUR;
  }
}
