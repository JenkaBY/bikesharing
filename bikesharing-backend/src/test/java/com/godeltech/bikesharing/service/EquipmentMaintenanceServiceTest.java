package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.database.rider.core.api.dataset.DataSet;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import org.junit.jupiter.api.Test;

public class EquipmentMaintenanceServiceTest extends AbstractIntegrationTest {
  private static final String SERVICE_STATUS = "SERVICE";
  private final EquipmentItemModel equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);

  @Test
  @DataSet(value = {"/dataset/equipmentStatusWithGroup.yml",
      "/dataset/serviceType.yml"}, cleanBefore = true)
  public void shouldPutEquipmentHandlingRequestProperly() {
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