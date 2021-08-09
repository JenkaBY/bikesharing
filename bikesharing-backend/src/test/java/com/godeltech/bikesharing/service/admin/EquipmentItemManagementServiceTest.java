package com.godeltech.bikesharing.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.models.request.EquipmentItemRequest;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.EquipmentStatusUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EquipmentItemManagementServiceTest extends AbstractIntegrationTest {
  private static final Long ID = 1L;
  private static final String COMMENT = "Needs to be serviced";

  private static EquipmentItemRequest request;

  @BeforeEach
  public void setUp() {
    request = EquipmentItemUtils.getEquipmentItemRequest();
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml"},
      cleanBefore = true, useSequenceFiltering = false)
  public void shouldSaveNewEquipmentItem() {
    request.setEquipmentStatusCode(null);
    var equipmentItem = equipmentItemMapper.mapToModel(request);

    var actual = equipmentItemManagementService.create(equipmentItem);

    assertEquals(ID, actual.getId());
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml",
      "/dataset/equipmentItem/equipmentItemFree.yml"},
      cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = "/dataset/equipmentItem/equipmentItemUpdated.yml")
  public void shouldUpdateEquipmentItem() {
    request.setComment(COMMENT);
    var equipmentItem = equipmentItemMapper.mapToModel(request);
    var expected = EquipmentItemUtils.getEquipmentItemModel(ID);
    expected.setComments(COMMENT);

    var actual = equipmentItemManagementService.update(equipmentItem, ID);

    assertEquals(expected, actual);
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml",
      "/dataset/equipmentItem/equipmentItemFree.yml"},
      cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = "/dataset/equipmentItem/equipmentItemBroken.yml")
  public void shouldMarkEquipmentItemBroken() {
    var equipmentStatusBroken = EquipmentStatusUtils.getEquipmentStatusBrokenModel();
    var expected = EquipmentItemUtils.getEquipmentItemModel(ID);
    expected.setEquipmentStatus(equipmentStatusBroken);
    var registrationNumber = expected.getRegistrationNumber();

    equipmentItemManagementService.setOutOfUse(registrationNumber);
    var actual = equipmentItemService.getByRegistrationNumber(registrationNumber);

    assertEquals(expected, actual);
  }
}
