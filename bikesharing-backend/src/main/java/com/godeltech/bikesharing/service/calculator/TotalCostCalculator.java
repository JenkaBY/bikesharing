package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;

public interface TotalCostCalculator {
  Long calculateTotalCost(RentCostModel costModel, RentTimeModel rentTimeModel);

  RentTimeUnit getType();
}
