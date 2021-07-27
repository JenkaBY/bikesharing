package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.CalculatedFinishRentDetailsModel;
import com.godeltech.bikesharing.models.CalculatedRentDetailsModel;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.service.RentCostService;
import java.time.Duration;
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


  public CalculatedFinishRentDetailsModel getCalculatedFinishRentDetails(RentOperationModel rentOperation,
                                                                         LocalDateTime actualFinishedAtTime) {
    var totalCost = rentOperation.getTotalCost();
    var deposit = rentOperation.getDeposit();
    var rentTimeModel = rentOperation.getRentTimeModel();
    var startTimeFromBase = rentOperation.getStartTime();
    var finishedAtTimeFromBase = rentOperation.getFinishedAtTime();

    var actualRentDuration = getRentDurationInMinutes(startTimeFromBase, actualFinishedAtTime);
    if (!is10minutesFromStartPassed(actualRentDuration)) {
      return new CalculatedFinishRentDetailsModel(0L,
          0L, deposit);
    }

    var paidRentDuration = getRentDurationInMinutes(startTimeFromBase, finishedAtTimeFromBase);
    var expiredRentDuration = actualRentDuration - paidRentDuration;
    if (isRentTimeExpired(expiredRentDuration)) {
      var extraAmountToBePaid = getExtraAmountToBePaid(expiredRentDuration, totalCost, paidRentDuration);
      return new CalculatedFinishRentDetailsModel(totalCost + extraAmountToBePaid,
          extraAmountToBePaid, 0L);
    }
    //Normally if rentTime is not Expired and equipment is in use for more then 10 minutes,
    // initially calculated TotalCost doesn't change
    return new CalculatedFinishRentDetailsModel(totalCost,
        getToBePaidAmount(totalCost, deposit), getToBeRefundAmount(totalCost, deposit));
  }

  private long getExtraAmountToBePaid(Long expiredRentDuration, Long totalCost, Long paidRentDuration) {
    var actualCostOf5MinutesRentTime = (double) totalCost / paidRentDuration * 5;
    return (long) (expiredRentDuration / 5 * actualCostOf5MinutesRentTime);
  }

  private long getToBeRefundAmount(long totalCost, Long deposit) {
    if (totalCost < deposit) {
      return deposit - totalCost;
    }
    return 0L;
  }

  private long getToBePaidAmount(long totalCost, Long deposit) {
    if (totalCost > deposit) {
      return totalCost - deposit;
    }
    return 0L;
  }

  private boolean isRentTimeExpired(Long actualRentDuration) {
    return actualRentDuration > 7;
  }

  private boolean is10minutesFromStartPassed(long actualDuration) {
    return actualDuration > 10;
  }

  private Long getRentDurationInMinutes(LocalDateTime from, LocalDateTime to) {
    Duration duration = Duration.between(from, to);
    return duration.toMinutes();
  }


}

