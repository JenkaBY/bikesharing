package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.RentCostModel;
import org.springframework.stereotype.Component;

//TODO implement me
@Component
public class TotalCostHalfHourCalculator implements TotalCostCalculator {

  @Override
  public Long calculateTotalCost(RentCostModel costModel) {
    return null;
  }

  @Override
  public String getType() {
    return "HALF_HOUR";
  }
}
