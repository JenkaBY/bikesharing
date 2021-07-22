package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.RentCostModel;

// TODO
public interface TotalCostCalculator {
  Long calculateTotalCost(RentCostModel costModel); //, RentTimeModel rentTimeModel);
//  RentTimeUnit getType();
  String getType();
}
