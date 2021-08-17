package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.lookup.ServiceTypeModel;
import com.godeltech.bikesharing.persistence.entity.lookup.ServiceType;

public class ServiceTypeUtils {
  public static final String NAME = "required periodical maintenance";
  public static final String CODE = ServiceTypeModel.SERVICE_TYPE_REQUIRED;
  public static final Long ID = 1L;

  public static ServiceType getServiceType() {
    var serviceType = new ServiceType();
    serviceType.setId(ID);
    serviceType.setName(NAME);
    serviceType.setCode(CODE);
    return serviceType;
  }

  public static ServiceTypeModel getServiceTypeModel() {
    var serviceTypeModel = new ServiceTypeModel();
    serviceTypeModel.setId(ID);
    serviceTypeModel.setName(NAME);
    serviceTypeModel.setCode(CODE);
    return serviceTypeModel;
  }
}
