package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.EquipmentHandlingRequest;

public interface EquipmentHandlingService {
  ServiceOperationModel putEquipmentHandlingRequest(EquipmentHandlingRequest request);

  ServiceOperationModel getById(Long id);
}
