package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentOperationModel;
import java.util.List;

public interface RentService {

  RentOperationModel startRentOperation(RentOperationModel model);

  RentOperationModel getByEquipmentItemRegistrationNumberAndRentStatusCode(String registrationNumber, String code);

  RentOperationModel finishRentOperation(RentOperationModel rentOperation, Long id);

  RentOperationModel getById(Long id);

  List<RentOperationModel> getAllByStatusCode(String statusCode);
}
