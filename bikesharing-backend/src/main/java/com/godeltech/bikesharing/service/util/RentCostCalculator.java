package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.exception.RentCostCalculationException;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.service.RentCostService;
import com.godeltech.bikesharing.service.calculator.TotalCostCalculatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
//TODO move to 'calculator' package
@Component
@RequiredArgsConstructor
public class RentCostCalculator {
  private final RentCostService rentCostService;
  private final TotalCostCalculatorFactory costCalculatorFactory;

// TODO Method should return class CalculatedRentDetailsModel { long totalCost; long paidMinutes}
//  change method signature: Take equipmentCode and RentTimeUnit
  public void setFinishTimeAndTotalCost(RentOperationModel rentOperationModel) {
    var totalCost = getTotalCost(rentOperationModel);
    rentOperationModel.setTotalCost(totalCost);

    // get minutesCount to add to startTime
    var plusMinutesCount = getPlusMinutesCount(rentOperationModel.getTimeUnitCode(),
        rentOperationModel.getTimeUnitCount());
    rentOperationModel.setEndTime(rentOperationModel.getStartTime().plusMinutes(plusMinutesCount));
  }

  private Long getTotalCost(RentOperationModel rentOperationModel) {
    var rentCostModel = rentCostService.getByEquipmentGroupCode(rentOperationModel
        .getEquipmentItem().getEquipmentGroup().getCode());
//    TODO
//    return  costCalculatorFactory.getCalculator("type").calculateTotalCost(rentCostModel);
    var totalCost = 0L;
    if (rentOperationModel.getTimeUnitCode().equals(StartRentOperationRequest.DAY_CODE)) {
      totalCost = rentCostModel.getDayPrice() * rentOperationModel.getTimeUnitCount();
    } else if (rentOperationModel.getTimeUnitCode().equals(StartRentOperationRequest.ONE_HOUR_CODE)) {
      totalCost = calculateCost(rentCostModel.getOneHourPrice(), rentCostModel.getHourDiscount(),
          rentCostModel.getMinimalHourPrice(), rentOperationModel.getTimeUnitCount());
    } else if (rentOperationModel.getTimeUnitCode()
        .equals(StartRentOperationRequest.HALF_HOUR_CODE)) {
      totalCost = rentCostModel.getHalfHourPrice() * rentOperationModel.getTimeUnitCount();
    }
    return totalCost;
  }

  private long calculateCost(Long oneHourPrice, Long hourDiscount, Long minimalHourPrice, Long timeUnitCount) {
    var totalCost = oneHourPrice;
    var currentHourPrice = oneHourPrice;
    while (timeUnitCount > 1) {
      currentHourPrice = changeCurrentHourPrice(currentHourPrice, hourDiscount, minimalHourPrice);
      totalCost += currentHourPrice;
      timeUnitCount -= 1;
    }
    return totalCost;
  }

  private Long changeCurrentHourPrice(Long currentHourPrice, Long hourDiscount, Long minimalHourPrice) {
    if (currentHourPrice > minimalHourPrice) {
      return currentHourPrice - hourDiscount;
    } else {
      return currentHourPrice;
    }
  }

// TODO The same calculation should be in TimeUnitCode enum (code.getMinutes() * amount)
  private long getPlusMinutesCount(String timeUnitCode, Long timeUnitCount) {
    if (timeUnitCode.equals(StartRentOperationRequest.DAY_CODE)) {
      return 24 * 60 * timeUnitCount;
    } else if (timeUnitCode.equals(StartRentOperationRequest.ONE_HOUR_CODE)) {
      return 60 * timeUnitCount;
    } else if (timeUnitCode.equals(StartRentOperationRequest.HALF_HOUR_CODE)) {
      return 30 * timeUnitCount;
    } else {
      throw new RentCostCalculationException(
          String.format("TimeUnitCode: %s doesn't match to available rent time-periods", timeUnitCode));
    }
  }

}

