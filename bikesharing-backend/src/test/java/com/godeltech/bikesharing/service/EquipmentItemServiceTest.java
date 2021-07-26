package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.database.rider.core.api.dataset.DataSet;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.repository.EquipmentItemRepository;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EquipmentItemServiceTest extends AbstractIntegrationTest {

  private static final String IN_USE_STATUS = "IN_USE";
  private static final String SERVICE_STATUS = "SERVICE";
  private static final String FREE_STATUS = "FREE";
  private final EquipmentItemModel equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
  @Autowired
  private EquipmentItemRepository repository;
  private EquipmentItemModel savedEquipmentItem;

  private void setUp() {
    savedEquipmentItem = equipmentItemService.save(equipmentModel);
  }

  @AfterEach
  void tearDown() {
    repository.deleteById(savedEquipmentItem.getId());
    savedEquipmentItem = null;
  }

  @Test
  @DataSet(value = {"/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml"},
      cleanBefore = true)
  void createdEquipmentItemShouldHaveFreeStatus() {
    setUp();
    var actual = equipmentItemService
        .getEquipmentStatusCodeByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertEquals(FREE_STATUS, actual);
  }

  @Test
  @DataSet(value = {"/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml"}, cleanBefore = true)
  public void shouldSetEquipmentItemStatusInUse() {
    setUp();
    equipmentItemService.updateEquipmentItemStatus(equipmentModel.getRegistrationNumber(),
        EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_IN_USE);
    var actual = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertActualStatus(actual, IN_USE_STATUS);
  }

  @Test
  @DataSet(value = {"/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml"}, cleanBefore = true)
  public void shouldSetEquipmentItemStatusService() {
    setUp();
    equipmentItemService.updateEquipmentItemStatus(equipmentModel.getRegistrationNumber(),
        EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_SERVICE);
    var actual = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertActualStatus(actual, SERVICE_STATUS);
  }

  private void assertActualStatus(EquipmentItemModel actual, String statusCode) {
    assertNotNull(actual);
    assertNotNull(actual.getEquipmentStatus());
    assertEquals(statusCode, actual.getEquipmentStatus().getCode());
  }
}
