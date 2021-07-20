package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.persistence.repository.EquipmentItemRepository;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void setUp() {
    savedEquipmentItem = equipmentItemService.save(equipmentModel);
  }

  @AfterEach
  void tearDown() {
    repository.deleteById(savedEquipmentItem.getId());
    savedEquipmentItem = null;
  }

  @Test
  void createdEquipmentItemShouldHaveFreeStatus() {
    var actual = equipmentItemService
        .getEquipmentStatusCodeByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertEquals(FREE_STATUS, actual);
  }

  @Test
  public void shouldSetEquipmentItemStatusInUse() {
    equipmentItemService.setEquipmentItemStatusInUse(equipmentModel.getRegistrationNumber());

    var actual = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertActualStatus(actual, IN_USE_STATUS);
  }

  @Test
  public void shouldSetEquipmentItemStatusService() {
    equipmentItemService.setEquipmentItemStatusService(equipmentModel.getRegistrationNumber());

    var actual = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertActualStatus(actual, SERVICE_STATUS);
  }

  private void assertActualStatus(EquipmentItemModel actual, String statusCode) {
    assertNotNull(actual);
    assertNotNull(actual.getEquipmentStatus());
    assertEquals(statusCode, actual.getEquipmentStatus().getCode());
  }
}
