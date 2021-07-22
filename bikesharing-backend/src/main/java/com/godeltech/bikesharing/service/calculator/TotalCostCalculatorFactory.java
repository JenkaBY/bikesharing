package com.godeltech.bikesharing.service.calculator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO Implement me
@Component
public class TotalCostCalculatorFactory {
  private final Map<String, TotalCostCalculator> calculators;
  @Autowired

  public TotalCostCalculatorFactory(List<TotalCostCalculator> calculators) {
    this.calculators = calculators.stream()
        .collect(Collectors.toMap(TotalCostCalculator::getType, Function.identity()));
  }

  public TotalCostCalculator getCalculator(String type) {
    return calculators.get(type);
  }
}
