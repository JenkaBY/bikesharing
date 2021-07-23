package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;

public class RentTimeModelUtils {
  public static RentTimeModel getRentTimeModel(RentTimeUnit rentTimeUnit, Long timeUnitAmount) {
    var rentTimeModel = new RentTimeModel();
    rentTimeModel.setRentTimeUnit(rentTimeUnit);
    rentTimeModel.setAmount(timeUnitAmount);
    return rentTimeModel;
  }
}
