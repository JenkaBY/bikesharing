package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import org.junit.jupiter.api.Test;

public class EquipmentItemServiceTest extends AbstractIntegrationTest {
  private static final String IN_USE_STATUS = "IN_USE";
  private static final String SERVICE_STATUS = "SERVICE";

  @Test
  public void shouldSetEquipmentItemStatusInUse() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);
    equipmentItemService.setEquipmentItemStatusInUse(equipmentModel.getRegistrationNumber());
    var equipmentFromBase = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertNotNull(equipmentFromBase);
    assertEquals(IN_USE_STATUS, equipmentFromBase.getEquipmentStatus().getCode());
  }

  @Test
  public void shouldSetEquipmentItemStatusService() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);
    assertEquals(equipmentItemService.getEquipmentStatusCodeByRegistrationNumber(
        equipmentModel.getRegistrationNumber()), "FREE");
    equipmentItemService.setEquipmentItemStatusService(equipmentModel.getRegistrationNumber());
    var equipmentFromBase = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertNotNull(equipmentFromBase);
    assertEquals(SERVICE_STATUS, equipmentFromBase.getEquipmentStatus().getCode());
  }

}