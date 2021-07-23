package com.godeltech.bikesharing.service.calculator;

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
    return calculators.get(type);
  }
}
