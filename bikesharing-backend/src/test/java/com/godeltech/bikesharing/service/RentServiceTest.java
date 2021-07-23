package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.database.rider.core.api.dataset.DataSet;
import com.godeltech.bikesharing.exception.ResourceNotFreeException;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.EquipmentStatusUtils;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import org.junit.jupiter.api.Test;

public class RentServiceTest extends AbstractIntegrationTest {
  private static final String IN_USE_STATUS = "IN_USE";
  public static final RentTimeUnit TIME_UNIT_HOUR = RentTimeUnit.HOUR;
  public static final Long TIME_UNIT_AMOUNT = 1L;
  private final RentOperationModel rentOperationModel = RentOperationUtils.getRentOperationModel(null);

  @Test
  @DataSet(value = {"/dataset/equipmentStatusWithGroup.yml",
      "/dataset/rentStatus.yml", "/dataset/rentCost.yml",
      "/dataset/equipmentItem.yml"}, cleanBefore = true)
  public void shouldStartRentOperationProperly() {
    rentOperationModel.setRentTimeModel(RentTimeModelUtils.getRentTimeModel(TIME_UNIT_HOUR,TIME_UNIT_AMOUNT));
    var savedRentOperationModel= rentService.startRentOperation(rentOperationModel);
    var rentOperationModelFromBase = rentService.getById(savedRentOperationModel.getId());
    var equipmentItemFromBase = equipmentItemService.getByRegistrationNumber(
        rentOperationModelFromBase.getEquipmentItem().getRegistrationNumber());
    var clientAccountFromBase = clientService.getByPhoneNumber(
        rentOperationModel.getClientAccount().getPhoneNumber());

    assertNotNull(clientAccountFromBase);
    assertEquals(savedRentOperationModel,rentOperationModelFromBase);
    assertEquals(rentOperationModelFromBase.getEquipmentItem().getRegistrationNumber(),
        savedRentOperationModel.getEquipmentItem().getRegistrationNumber());
    assertEquals(rentOperationModelFromBase.getClientAccount().getPhoneNumber(),
        savedRentOperationModel.getClientAccount().getPhoneNumber());
    assertEquals(IN_USE_STATUS, equipmentItemFromBase.getEquipmentStatus().getCode());
  }

  @Test
  @DataSet(value = "/dataset/equipmentStatusWithGroup.yml", cleanBefore = true)
  public void shouldThrowException() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentModel.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatusServiceModel());
    equipmentItemService.save(equipmentModel);
    var rentOperationModel = RentOperationUtils.getRentOperationModel(null);
    assertThrows(ResourceNotFreeException.class,
        () -> rentService.startRentOperation(rentOperationModel));
  }

}