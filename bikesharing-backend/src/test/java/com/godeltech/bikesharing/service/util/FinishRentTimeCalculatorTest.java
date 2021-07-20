package com.godeltech.bikesharing.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.models.lookup.TimePeriodModel;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.RentCostUtils;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import com.godeltech.bikesharing.utils.TimePeriodUtils;
import org.junit.jupiter.api.Test;

class FinishRentTimeCalculatorTest extends AbstractIntegrationTest {
  private static final Long ID = 1L;
  private static final Long COST_FOR_3_HOURS = 5L;

  @Test
  public void shouldCalculateProperly() {
    var equipmentModel = EquipmentItemUtils.getEquipmentItemModel(null);
    equipmentItemService.save(equipmentModel);
    var rentCostModel1 = RentCostUtils.getRentCostModel(null);
    rentCostService.save(rentCostModel1);

    var rentCostModel2 = RentCostUtils.getRentCostModel(null);
    rentCostModel2.setCost(COST_FOR_3_HOURS);
    var timePeriodModel = TimePeriodUtils.getTimePeriodModel();
    timePeriodModel.setCode(TimePeriodModel.PERIOD_3_HOURS_CODE);
    rentCostModel2.setTimePeriod(timePeriodModel);
    rentCostService.save(rentCostModel2);


    //TODO continue
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
  }
}