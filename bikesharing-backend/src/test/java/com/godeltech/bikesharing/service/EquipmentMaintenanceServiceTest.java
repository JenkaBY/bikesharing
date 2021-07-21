package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import org.junit.jupiter.api.Test;

public class EquipmentMaintenanceServiceTest extends AbstractIntegrationTest {
  private static final String SERVICE_STATUS = "SERVICE";

  @Test
  public void shouldPutEquipmentHandlingRequestProperly() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);
    var request = ServiceOperationUtils.getEquipmentHandlingRequest();
    var serviceOperationModel = equipmentMaintenanceService.putEquipmentHandlingRequest(request);
    var serviceOperationModelFromBase = equipmentMaintenanceService.getById(serviceOperationModel.getId());
    var equipmentFromBase = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertNotNull(serviceOperationModelFromBase.getId());
    assertNotNull(equipmentFromBase);
    assertEquals(serviceOperationModelFromBase.getServiceTypeModel().getCode(),
        serviceOperationModel.getServiceTypeModel().getCode());
    assertEquals(equipmentFromBase.getEquipmentStatus().getCode(), SERVICE_STATUS);
  }

}