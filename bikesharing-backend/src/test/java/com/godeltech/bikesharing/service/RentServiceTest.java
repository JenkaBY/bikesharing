package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.godeltech.bikesharing.exception.ResourceNotFreeException;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.EquipmentStatusUtils;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import org.junit.jupiter.api.Test;

public class RentServiceTest extends AbstractIntegrationTest {
  private static final String IN_USE_STATUS = "IN_USE";

  @Test
  public void shouldStartRentOperationProperly() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);
    var rentOperationModel = rentOperationMapper.mapToModel(RentOperationUtils.getRentOperationRequest());
    var rentOperationResponse = rentService.startRentOperation(rentOperationModel);
    var rentOperationModelFromBase = rentService.getById(rentOperationResponse.getId());
    var equipmentFromBase = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    assertNotNull(rentOperationModelFromBase.getId());
    assertNotNull(equipmentFromBase);
    assertNotNull(clientService.getByPhoneNumber(rentOperationModel.getClientAccount().getPhoneNumber()));
    assertEquals(rentOperationModelFromBase.getEquipmentItem().getRegistrationNumber(),
        rentOperationResponse.getEquipmentItem().getRegistrationNumber());
    assertEquals(rentOperationModelFromBase.getClientAccount().getPhoneNumber(),
        rentOperationResponse.getClientAccount().getPhoneNumber());
    assertEquals(equipmentFromBase.getEquipmentStatus().getCode(), IN_USE_STATUS);
  }

  @Test
  public void shouldThrowException() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentModel.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatusServiceModel());
    equipmentItemService.save(equipmentModel);
    var rentOperationModel = RentOperationUtils.getRentOperationModel(null);
    assertThrows(ResourceNotFreeException.class,
        () -> rentService.startRentOperation(rentOperationModel));
  }

}