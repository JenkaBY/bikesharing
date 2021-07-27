package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentOperationModel;

public interface RentService {
  RentOperationModel startRentOperation(RentOperationModel model);

  RentOperationModel getByEquipmentItemRegistrationNumberAndRentStatusCode(String registrationNumber, String code);

  RentOperationModel finishRentOperation(RentOperationModel rentOperation);

  RentOperationModel getById(Long id);
}
