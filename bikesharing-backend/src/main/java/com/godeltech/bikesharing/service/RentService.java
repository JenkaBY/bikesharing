package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentOperationModel;

public interface RentService {
  RentOperationModel startRentOperation(RentOperationModel model);

  RentOperationModel getById(Long id);
}
