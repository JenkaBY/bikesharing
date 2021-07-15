package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.RentOperationRequest;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
import javax.validation.Valid;

public interface RentService {
  RentOperationResponse startRentOperation(@Valid RentOperationRequest request);

  RentOperationModel getById(Long id);
}
