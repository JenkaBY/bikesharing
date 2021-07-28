package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ServiceOperationModel;

public interface EquipmentMaintenanceService {
  ServiceOperationModel startEquipmentServiceOperation(ServiceOperationModel serviceOperation);

  ServiceOperationModel finishEquipmentServiceOperation(ServiceOperationModel serviceOperation);

  ServiceOperationModel getById(Long id);
}
