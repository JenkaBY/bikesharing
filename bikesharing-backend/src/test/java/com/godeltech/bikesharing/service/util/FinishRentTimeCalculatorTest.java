package com.godeltech.bikesharing.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class FinishRentTimeCalculatorTest extends AbstractIntegrationTest {
  private static final Long DEPOSIT = 300L;
  private static final Long PLUS_MINUTES = 160L;

  @Test
  public void shouldCalculateProperly() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);

    var rentOperationModel = rentOperationMapper.mapToModel(RentOperationUtils.getRentOperationRequest());
    rentOperationModel.setDeposit(DEPOSIT);
    var rentOperationResponse = rentService.startRentOperation(rentOperationModel);
    var rentOperationModelFromBase = rentService.getById(rentOperationResponse.getId());
    var equipmentFromBase = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    final LocalDateTime startTime = rentOperationResponse.getStartTime();
    final LocalDateTime endTime = startTime.plusMinutes(PLUS_MINUTES);
    assertNotNull(rentOperationModelFromBase.getId());
    assertNotNull(equipmentFromBase);

    assertEquals(endTime, rentOperationResponse.getEndTime());
  }
}