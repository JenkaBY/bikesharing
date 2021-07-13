package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentStatusModel;
import com.godeltech.bikesharing.persistence.entity.RentStatus;

public class RentStatusUtils {
  public static final String NAME = "NewRentStatus";
  public static final String CODE = "LASTING";

  public static RentStatus getRentStatus() {
    var rentStatus = new RentStatus();
    rentStatus.setName(NAME);
    rentStatus.setCode(CODE);
    return rentStatus;
  }

  public static RentStatusModel getRentStatusModel() {
    var rentStatusModel = new RentStatusModel();
    rentStatusModel.setName(NAME);
    rentStatusModel.setCode(CODE);
    return rentStatusModel;
  }
}
