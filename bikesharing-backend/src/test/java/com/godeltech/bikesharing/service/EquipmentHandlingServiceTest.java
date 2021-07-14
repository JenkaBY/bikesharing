package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import org.junit.jupiter.api.Test;

public class EquipmentHandlingServiceTest extends AbstractIntegrationTest {
  private static final String SERVICE_STATUS = "SERVICE";

  @Test
  public void shouldPutEquipmentHandlingRequestProperly() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);
    var request = ServiceOperationUtils.getEquipmentHandlingRequest();
    var serviceOperationModel = equipmentHandlingService.putEquipmentHandlingRequest(request);
    var serviceOperationModelFromBase = equipmentHandlingService.getById(serviceOperationModel.getId());
    var equipmentFromBase = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertNotNull(serviceOperationModelFromBase.getId());
    assertNotNull(equipmentFromBase);
    assertEquals(serviceOperationModelFromBase, serviceOperationModel);
    assertEquals(equipmentFromBase.getEquipmentStatus().getCode(), SERVICE_STATUS);
  }

}