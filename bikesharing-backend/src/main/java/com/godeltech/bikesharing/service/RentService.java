package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.RentOperationRequest;
import com.godeltech.bikesharing.models.response.RentOperationResponse;

public interface RentService {
  RentOperationResponse startRentOperation(RentOperationRequest request);

  RentOperationModel getById(Long id);
}
