package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.database.rider.core.api.dataset.DataSet;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import org.junit.jupiter.api.Test;

public class EquipmentMaintenanceServiceTest extends AbstractIntegrationTest {

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml",
      "/dataset/serviceType/serviceTypeAll.yml",
      "/dataset/equipmentItem/equipmentItemFree.yml"
  },
      cleanBefore = true, useSequenceFiltering = false)
  public void shouldPutEquipmentHandlingRequestProperly() {
    var request = ServiceOperationUtils.getEquipmentHandlingRequest();

    var actualServiceOperation = equipmentMaintenanceService.putEquipmentHandlingRequest(request);
    var actualEquipmentItem = equipmentItemService.getByRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);

    var expectedServiceOperation = equipmentMaintenanceService.getById(actualServiceOperation.getId());
    assertNotNull(actualServiceOperation);
    assertEquals(expectedServiceOperation, actualServiceOperation);
    assertEquals(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_SERVICE,
        actualEquipmentItem.getEquipmentStatus().getCode());
  }

}
