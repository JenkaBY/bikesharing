package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.models.request.FinishEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class EquipmentMaintenanceServiceTest extends AbstractIntegrationTest {
  private static final StartEquipmentMaintenanceRequest startRequest =
      ServiceOperationUtils.getStartEquipmentMaintenanceRequest();
  private static final Long ID = 1L;
  private static final FinishEquipmentMaintenanceRequest finishRequest =
      ServiceOperationUtils.getFinishEquipmentMaintenanceRequest();

  @Test
  @DataSet(value = {
          "/dataset/equipmentGroup/equipmentGroupAll.yml",
          "/dataset/equipmentStatus/equipmentStatusAll.yml",
          "/dataset/serviceType/serviceTypeAll.yml",
          "/dataset/equipmentItem/equipmentItemFree.yml"
      },
      cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = {
      "/dataset/equipmentItem/equipmentItemInService.yml",
      "/dataset/serviceOperation/serviceOperationStarted.yml"
      })
  public void shouldStartServiceOperationProperly() {
    var serviceOperationModel = serviceOperationMapper.mapToModel(startRequest);
    var actualServiceOperation = equipmentMaintenanceService.startEquipmentServiceOperation(serviceOperationModel);
    var expectedServiceOperation = ServiceOperationUtils.getServiceOperationModel(ID);
    expectedServiceOperation.setFinishedOnDate(null);
    expectedServiceOperation.setStartDate(LocalDate.now());

    assertEquals(expectedServiceOperation, actualServiceOperation);
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml",
      "/dataset/serviceType/serviceTypeAll.yml",
      "/dataset/equipmentItem/equipmentItemInService.yml",
      "/dataset/serviceOperation/serviceOperationInitial.yml"
      },
      cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = {
      "/dataset/equipmentItem/equipmentItemFree.yml"
      })
  public void shouldFinishServiceOperationProperly() {
    var serviceOperationModel = serviceOperationMapper.mapToModel(finishRequest);
    var actualServiceOperation = equipmentMaintenanceService.finishEquipmentServiceOperation(serviceOperationModel, ID);
    var expectedServiceOperation = ServiceOperationUtils.getServiceOperationModel(ID);
    var expectedEquipmentItem = EquipmentItemUtils.getEquipmentItemModel(ID);
    expectedServiceOperation.setEquipmentItemModel(expectedEquipmentItem);
    expectedServiceOperation.setFinishedOnDate(ServiceOperationUtils.END_DATE);

    assertEquals(expectedServiceOperation, actualServiceOperation);
  }
}
