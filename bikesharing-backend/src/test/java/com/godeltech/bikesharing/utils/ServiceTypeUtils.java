package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.lookup.ServiceTypeModel;
import com.godeltech.bikesharing.persistence.entity.lookup.ServiceType;

public class ServiceTypeUtils {
  public static final String NAME = "NewServiceType";
  public static final String CODE = "SIMPLE_REPAIR";

  public static ServiceType getServiceType() {
    var serviceType = new ServiceType();
    serviceType.setName(NAME);
    serviceType.setCode(CODE);
    return serviceType;
  }

  public static ServiceTypeModel getServiceTypeModel() {
    var serviceTypeModel = new ServiceTypeModel();
    serviceTypeModel.setName(NAME);
    serviceTypeModel.setCode(CODE);
    return serviceTypeModel;
  }
}
