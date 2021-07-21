package com.godeltech.bikesharing.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class RentCostCalculatorTest extends AbstractIntegrationTest {
  private static final Long PLUS_MINUTES = 180L;
  private static final Long TIME_UNIT_COUNT = 3L;
  private static final Long TOTAL_COST = 9L;

  @Test
  public void shouldCalculateProperly() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);

    var rentOperationModel = rentOperationMapper.mapToModel(RentOperationUtils.getRentOperationRequest());
    rentOperationModel.setTimeUnitCount(TIME_UNIT_COUNT);
    var rentOperationModelResponse = rentService.startRentOperation(rentOperationModel);

    // TODO Delete it
    var rentOperationModelFromBase = rentService.getById(rentOperationModelResponse.getId());

    var equipmentFromBase = equipmentItemService.getByRegistrationNumber(equipmentModel.getRegistrationNumber());
    final LocalDateTime endTime = rentOperationModelResponse.getStartTime()
        .plusMinutes(PLUS_MINUTES);
    assertNotNull(rentOperationModelFromBase.getId());
    assertNotNull(equipmentFromBase);

    // TODO Delete it
    assertEquals(rentOperationModelResponse, rentOperationModelFromBase);

    assertEquals(endTime, rentOperationModelResponse.getEndTime());
    assertEquals(TOTAL_COST, rentOperationModelResponse.getTotalCost());
  }
}