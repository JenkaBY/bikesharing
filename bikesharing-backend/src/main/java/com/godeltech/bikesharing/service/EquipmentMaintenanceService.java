package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.EquipmentMaintenanceRequest;

public interface EquipmentMaintenanceService {
  ServiceOperationModel putEquipmentHandlingRequest(EquipmentMaintenanceRequest request);

  ServiceOperationModel getById(Long id);
}
