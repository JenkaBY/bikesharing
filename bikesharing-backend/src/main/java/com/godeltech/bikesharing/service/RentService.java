package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;

public interface RentService {
  RentOperationModel startRentOperation(StartRentOperationRequest request);

  RentOperationModel getById(Long id);
}
