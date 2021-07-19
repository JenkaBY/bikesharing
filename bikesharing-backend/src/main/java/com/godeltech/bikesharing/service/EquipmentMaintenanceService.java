package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.EquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.EquipmentMaintenanceResponse;

public interface EquipmentMaintenanceService {
  EquipmentMaintenanceResponse putEquipmentHandlingRequest(EquipmentMaintenanceRequest request);

  ServiceOperationModel getById(Long id);
}
