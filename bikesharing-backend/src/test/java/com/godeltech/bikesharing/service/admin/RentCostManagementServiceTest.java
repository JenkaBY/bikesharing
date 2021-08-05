package com.godeltech.bikesharing.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.exception.ResourceExistingPersistenceException;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.EquipmentGroupUtils;
import com.godeltech.bikesharing.utils.RentCostUtils;
import org.junit.jupiter.api.Test;

class RentCostManagementServiceTest extends AbstractIntegrationTest {
  private static final Long ID = 1L;
  private static final RentCostModel rentCostModel = RentCostUtils.getRentCostModel(null);
  private static final String GROUP_CODE = EquipmentGroupUtils.CODE;

  @Test
  @DataSet(value = "/dataset/equipmentGroup/equipmentGroupAll.yml",
      cleanBefore = true, useSequenceFiltering = false)
  public void shouldSaveNewRentCost() {

    var actual = rentCostManagementService.saveWithGroupCode(rentCostModel, GROUP_CODE);

    assertEquals(ID, actual.getId());
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/rentCost/rentCostAll.yml"},
      cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = "/dataset/rentCost/rentCostUpdated.yml")
  public void shouldUpdateRentCost() {
    rentCostModel.setDayPrice(20L);
    rentCostModel.setOneHourPrice(8L);

    var actual = rentCostManagementService.update(rentCostModel, ID);

    rentCostModel.setId(ID);
    assertEquals(rentCostModel, actual);
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/rentCost/rentCostAll.yml"},
      cleanBefore = true, useSequenceFiltering = false)
  public void shouldFailWithResourceExistingPersistenceException() {

    assertThrows(ResourceExistingPersistenceException.class,
        () -> rentCostManagementService.update(rentCostModel, ID + 1));
  }
}