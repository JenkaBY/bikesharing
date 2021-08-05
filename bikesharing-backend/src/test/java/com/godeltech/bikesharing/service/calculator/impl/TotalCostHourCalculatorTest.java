package com.godeltech.bikesharing.service.calculator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.utils.RentCostUtils;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TotalCostHourCalculatorTest {
  private static final RentCostModel rentcostmodel = RentCostUtils.getRentCostModel(null);
  private static final RentTimeModel rentTimemodel = RentTimeModelUtils.getRentTimeModel(RentTimeUnit.HOUR, 0L);

  private static final TotalCostHourCalculator calculator = new TotalCostHourCalculator();

  private static Stream<Arguments> provideRentTimeModels() {
    return Stream.of(
        Arguments.of(1L, rentcostmodel.getOneHourPrice()),
        Arguments.of(2L, 7L),
        Arguments.of(3L, 9L),
        Arguments.of(4L, 10L),
        Arguments.of(5L, 11L),
        Arguments.of(6L, 12L),
        Arguments.of(7L, 13L),
        Arguments.of(8L, 14L),
        Arguments.of(9L, 15L),
        Arguments.of(10L, 16L),
        Arguments.of(11L, 17L),
        Arguments.of(12L, 18L)
    );
  }

  @ParameterizedTest(name = "{index} total cost for TimeUnitAmount: {0} is calculated properly: {1}")
  @MethodSource(value = "provideRentTimeModels")
  void isTotalCostCalculatedProperly(Long amount, Long expected) {
    rentTimemodel.setAmount(amount);

    var actual = calculator.calculateTotalCost(rentcostmodel, rentTimemodel);

    assertEquals(expected, actual, "calculated actual totalCost is not equal to expected");
  }

}
