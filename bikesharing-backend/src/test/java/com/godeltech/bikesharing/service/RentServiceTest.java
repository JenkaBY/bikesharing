package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.exception.ResourceStatusNotAppropriateException;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RentServiceTest extends AbstractIntegrationTest {
  private static final RentTimeUnit TIME_UNIT_HOUR = RentTimeUnit.HOUR;
  private static final Long TIME_UNIT_AMOUNT = 1L;
  private static final Long MINUTES_PASSED = 120L;
  private static final Long ID = 1L;
  private static final String STATUS_LASTING = RentStatusModel.RENT_STATUS_LASTING;
  private final RentOperationModel rentOperationModel = RentOperationUtils.getRentOperationModel(null);

  @BeforeEach
  @DataSet(value = {
          "/dataset/clientAccount/clientAccountInitial.yml",
          "/dataset/equipmentGroup/equipmentGroupAll.yml",
          "/dataset/equipmentStatus/equipmentStatusAll.yml",
          "/dataset/rentStatus/rentStatusAll.yml",
          "/dataset/rentCost/rentCostAll.yml",
      },
      cleanBefore = true, useSequenceFiltering = false)
  public void setUp() {
  }

  @Test
  @DataSet(value = "/dataset/equipmentItem/equipmentItemAll.yml",
      useSequenceFiltering = false)
  @ExpectedDataSet(value = {"dataset/equipmentItem/equipmentItemInUse.yml",
      "dataset/clientAccount/clientAccountExpected.yml",
      "dataset/rentOperation/rentOperationLasting.yml"})
  public void shouldStartRentOperationProperly() {
    rentOperationModel.setRentTimeModel(RentTimeModelUtils.getRentTimeModel(TIME_UNIT_HOUR, TIME_UNIT_AMOUNT));

    var actualRentOperation = rentService.startRentOperation(rentOperationModel);

    assertEquals(rentService.getById(actualRentOperation.getId()), actualRentOperation);
  }

  @Test
  @DataSet(value = "/dataset/equipmentItem/equipmentItemInUse.yml",
      useSequenceFiltering = false)
  public void shouldThrowException() {
    assertThrows(ResourceStatusNotAppropriateException.class,
        () -> rentService.startRentOperation(rentOperationModel));
  }

  @Test
  @DataSet(value = {
          "/dataset/equipmentItem/equipmentItemInUse.yml",
          "dataset/clientAccount/clientAccountInitial.yml",
          "dataset/rentOperation/rentOperationInitial.yml"
      },
      useSequenceFiltering = false)
  @ExpectedDataSet(value = {
      "dataset/equipmentItem/equipmentItemAll.yml",
      "dataset/rentOperation/rentOperationClosed.yml",
      "/dataset/timeInUse/timeInUseCreated.yml"
      })
  public void shouldFinishRentOperationProperly() throws InterruptedException {
    var finishRentOperationRequest = RentOperationUtils.getFinishRentOperationRequest();
    finishRentOperationRequest.setFinishedAtTime(rentOperationModel.getStartTime().plusMinutes(MINUTES_PASSED));
    var rentOperationModel = rentOperationMapper.mapToModel(finishRentOperationRequest);

    var actualRentOperation = rentService.finishRentOperation(rentOperationModel, ID);

    var expectedRentOperation = rentService.getById(actualRentOperation.getId());
    expectedRentOperation.setToBePaidAmount(4L);
    expectedRentOperation.setToBeRefundAmount(0L);
    Thread.sleep(500);

    assertEquals(expectedRentOperation, actualRentOperation);
  }

  @Test
  @DataSet(value = {
      "/dataset/equipmentItem/equipmentItemInUse.yml",
      "dataset/clientAccount/clientAccountInitial.yml",
      "dataset/rentOperation/rentOperationInitial.yml"
      },
      useSequenceFiltering = false)
  public void shouldGetListOfItemsWithStatus() {

    var actualService = rentService.getAllByStatusCode(STATUS_LASTING);

    assertEquals(1, actualService.size());
  }

}