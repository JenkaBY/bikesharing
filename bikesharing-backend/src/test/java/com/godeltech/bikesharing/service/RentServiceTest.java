package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.exception.ResourceStatusNotAppropriateException;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.EquipmentStatusUtils;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import org.junit.jupiter.api.Test;

public class RentServiceTest extends AbstractIntegrationTest {
  private static final RentTimeUnit TIME_UNIT_HOUR = RentTimeUnit.HOUR;
  private static final Long TIME_UNIT_AMOUNT = 1L;
  private final RentOperationModel rentOperationModel = RentOperationUtils.getRentOperationModel(null);

  @Test
  @DataSet(value = {"/dataset/clientAccount/clientAccountInitial.yml",
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml",
      "/dataset/rentStatus/rentStatusAll.yml",
      "/dataset/rentCost/rentCostAll.yml",
      "/dataset/equipmentItem/equipmentItemInitial.yml"}, cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = {"dataset/equipmentItem/equipmentItemExpected.yml",
      "dataset/clientAccount/clientAccountExpected.yml",
      "dataset/rentOperation/rentOperationExpected.yml"})
  public void shouldStartRentOperationProperly() {
    rentOperationModel.setRentTimeModel(RentTimeModelUtils.getRentTimeModel(TIME_UNIT_HOUR, TIME_UNIT_AMOUNT));

    var savedRentOperationModel = rentService.startRentOperation(rentOperationModel);

    assertEquals(savedRentOperationModel, rentService.getById(savedRentOperationModel.getId()));
  }

  @Test
  @DataSet(value = {"/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml"}, cleanBefore = true)
  public void shouldThrowException() {
//    TODO You can can initialize equipment with SERVICE status in @DataSet
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentModel.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatusServiceModel());
    equipmentItemService.save(equipmentModel);

    var rentOperationModel = RentOperationUtils.getRentOperationModel(null);

    assertThrows(ResourceStatusNotAppropriateException.class,
        () -> rentService.startRentOperation(rentOperationModel));
  }

}
