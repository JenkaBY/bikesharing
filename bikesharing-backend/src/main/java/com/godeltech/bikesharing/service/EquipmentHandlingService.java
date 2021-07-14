package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.EquipmentHandlingRequest;
import com.godeltech.bikesharing.models.response.EquipmentHandlingResponse;

public interface EquipmentHandlingService {
  EquipmentHandlingResponse putEquipmentHandlingRequest(EquipmentHandlingRequest request);

  ServiceOperationModel getById(Long id);
}
