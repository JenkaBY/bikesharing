package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.database.rider.core.api.dataset.DataSet;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EquipmentItemServiceTest extends AbstractIntegrationTest {
  private static final String STATUS_FREE = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE;
  private static final String STATUS_SERVICE = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_SERVICE;
  private static final String STATUS_IN_USE = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_IN_USE;
  private final EquipmentItemModel equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);

  @BeforeEach
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml",
      "/dataset/equipmentItem/equipmentItemFree.yml"
      },
      cleanBefore = true, useSequenceFiltering = false)
  public void setUp() {
  }

  @Test
  void createdEquipmentItemShouldHaveFreeStatus() {
    var actual = equipmentItemService
        .getEquipmentStatusCodeByRegistrationNumber(equipmentModel.getRegistrationNumber());

    assertEquals(STATUS_FREE, actual);
  }

  @Test
  public void shouldSetEquipmentItemStatusInUse() {
    equipmentItemService.updateEquipmentItemStatus(equipmentModel.getRegistrationNumber(), STATUS_IN_USE);

    var actual = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());

    assertActualStatus(STATUS_IN_USE, actual);
  }

  @Test
  public void shouldSetEquipmentItemStatusService() {
    equipmentItemService.updateEquipmentItemStatus(equipmentModel.getRegistrationNumber(), STATUS_SERVICE);

    var actual = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());

    assertActualStatus(STATUS_SERVICE, actual);
  }

  private void assertActualStatus(String statusCode, EquipmentItemModel actual) {
    assertNotNull(actual);
    assertNotNull(actual.getEquipmentStatus());
    assertEquals(statusCode, actual.getEquipmentStatus().getCode());
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentItem/equipmentItemAll.yml"
  })
  public void shouldGetListOfItemsWithStatus() {
    equipmentItemService.updateEquipmentItemStatus(equipmentModel.getRegistrationNumber(), STATUS_SERVICE);

    var actualFree = equipmentItemService.getAllByStatusCode(STATUS_FREE);
    var actualService = equipmentItemService.getAllByStatusCode(STATUS_SERVICE);

    assertEquals(5, actualFree.size());
    assertEquals(1, actualService.size());
  }
}
