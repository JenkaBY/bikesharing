package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.CalculatedRentDetailsModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.service.RentCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentCostTimeCalculator {
  private final RentCostService rentCostService;
  private final TotalCostCalculatorFactory costCalculatorFactory;

  public CalculatedRentDetailsModel getCalculatedRentDetails(String equipmentGroupCode, RentTimeModel rentTimeModel) {
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

}

