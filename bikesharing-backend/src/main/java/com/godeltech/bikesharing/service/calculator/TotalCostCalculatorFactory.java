package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TotalCostCalculatorFactory {

  private final Map<RentTimeUnit, TotalCostCalculator> calculators;

  public TotalCostCalculatorFactory(List<TotalCostCalculator> calculators) {
    this.calculators = calculators.stream()
        .collect(Collectors.toMap(TotalCostCalculator::getType, Function.identity()));
  }

  public TotalCostCalculator getCalculator(RentTimeUnit type) {
    return calculators.getOrDefault(type, getDefaultCalculator(type));
  }

  private TotalCostCalculator getDefaultCalculator(RentTimeUnit type) {
    return new TotalCostCalculator() {
      @Override
      public Long calculateTotalCost(RentCostModel costModel, RentTimeModel rentTimeModel) {
        var errorMessage = String.format("Total Cost Calculator is not implemented for %s RentTimeUnit", type);
        throw new RuntimeException(errorMessage);
      }

      @Override
      public RentTimeUnit getType() {
        return null;
      }
    };
  }
}
