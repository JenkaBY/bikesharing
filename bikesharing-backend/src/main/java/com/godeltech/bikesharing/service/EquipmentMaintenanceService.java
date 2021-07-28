package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;

public interface EquipmentMaintenanceService {
  ServiceOperationModel startEquipmentServiceOperation(StartEquipmentMaintenanceRequest request);

  ServiceOperationModel getById(Long id);
}
