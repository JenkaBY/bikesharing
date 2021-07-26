package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.CalculatedFinishRentDetailsModel;
import com.godeltech.bikesharing.models.CalculatedRentDetailsModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.service.RentCostService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentCostTimeCalculator {
  private final RentCostService rentCostService;
  private final TotalCostCalculatorFactory costCalculatorFactory;

  public CalculatedRentDetailsModel getCalculatedStartRentDetails(String equipmentGroupCode,
                                                                  RentTimeModel rentTimeModel) {
    var totalCost = getTotalCost(equipmentGroupCode, rentTimeModel);
    var paidMinutes = getPaidMinutes(rentTimeModel);

    return new CalculatedRentDetailsModel(totalCost, paidMinutes);
  }

  private Long getTotalCost(String equipmentGroupCode, RentTimeModel rentTimeModel) {
    var rentCostModel = rentCostService.getByEquipmentGroupCode(equipmentGroupCode);
    return costCalculatorFactory.getCalculator(rentTimeModel.getRentTimeUnit())
        .calculateTotalCost(rentCostModel, rentTimeModel);
  }

  private long getPaidMinutes(RentTimeModel rentTimeModel) {
    return rentTimeModel.getAmount() * rentTimeModel.getRentTimeUnit().getDurationInMin();
  }

  public CalculatedFinishRentDetailsModel getCalculatedFinishRentDetails(String code, LocalDateTime finishedAtTime) {
    var totalCost = 0L;
    var toBePaidAmount = 0L;
    var toBeRefundAmount = 0L;

    return new CalculatedFinishRentDetailsModel(totalCost, toBePaidAmount, toBeRefundAmount);
  }


}

