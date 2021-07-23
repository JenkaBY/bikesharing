package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.request.RentTimeRequest;

public class RentTimeModelUtils {
  public static RentTimeModel getRentTimeModel(RentTimeUnit rentTimeUnit, Long timeUnitAmount) {
    var rentTimeModel = new RentTimeModel();
    rentTimeModel.setRentTimeUnit(rentTimeUnit);
    rentTimeModel.setAmount(timeUnitAmount);
    return rentTimeModel;
  }

  public static RentTimeRequest getRentTimeRequest(RentTimeUnit rentTimeUnit, Long timeUnitAmount) {
    var rentTimeRequest = new RentTimeRequest();
    rentTimeRequest.setRentTimeUnit(rentTimeUnit);
    rentTimeRequest.setAmount(timeUnitAmount);
    return rentTimeRequest;
  }

}
