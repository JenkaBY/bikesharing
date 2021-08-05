package com.godeltech.bikesharing.service.equipmentmaintenance;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import java.util.List;

public interface EquipmentMaintenanceService {

  ServiceOperationModel startEquipmentServiceOperation(ServiceOperationModel serviceOperation);

  ServiceOperationModel finishEquipmentServiceOperation(ServiceOperationModel serviceOperation, Long id);

  ServiceOperationModel getById(Long id);

  List<ServiceOperationModel> getAllUnfinished();
}
