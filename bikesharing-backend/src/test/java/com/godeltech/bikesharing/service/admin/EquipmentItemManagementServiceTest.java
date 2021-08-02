package com.godeltech.bikesharing.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.EquipmentGroupUtils;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import org.junit.jupiter.api.Test;

class EquipmentItemManagementServiceTest extends AbstractIntegrationTest {
  private static final Long ID = 1L;
  private static final EquipmentItemModel equipmentItem = EquipmentItemUtils.getEquipmentItemModel(null);
  private static final String GROUP_CODE = EquipmentGroupUtils.CODE;
  private static final String COMMENT = "Needs to be serviced";

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml"},
      cleanBefore = true, useSequenceFiltering = false)
  public void shouldSaveNewEquipmentItem() {

    var actual = equipmentItemManagementService.saveWithGroupCode(equipmentItem, GROUP_CODE);

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
    equipmentItem.setComments(COMMENT);

    var actual = equipmentItemManagementService.update(equipmentItem, ID);

    equipmentItem.setId(ID);
    assertEquals(equipmentItem, actual);
  }
}