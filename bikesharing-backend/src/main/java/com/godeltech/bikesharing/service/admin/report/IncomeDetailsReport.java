package com.godeltech.bikesharing.service.admin.report;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;

public interface IncomeDetailsReport {
  Long calculateTotalCost(RentCostModel costModel, RentTimeModel rentTimeModel);

  RentTimeUnit getType();
}
