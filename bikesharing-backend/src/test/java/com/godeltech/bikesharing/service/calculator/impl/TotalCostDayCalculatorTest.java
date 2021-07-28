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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TotalCostDayCalculatorTest {
  private static final RentCostModel rentcostmodel = RentCostUtils.getRentCostModel(null);
  private static final RentTimeModel rentTimemodel = RentTimeModelUtils.getRentTimeModel(RentTimeUnit.DAY, 0L);
  private static final Long DAY_PRICE = rentcostmodel.getDayPrice();

  @Autowired
  private TotalCostDayCalculator dayCalculator;

  private static Stream<Arguments> provideRentTimeModels() {
    return Stream.of(
        Arguments.of(1L, DAY_PRICE),
        Arguments.of(2L, DAY_PRICE * 2),
        Arguments.of(3L, DAY_PRICE * 3),
        Arguments.of(4L, DAY_PRICE * 4)
    );
  }

  @ParameterizedTest(name = "{index} total cost for TimeUnitAmount: {0} is calculated properly: {1}")
  @MethodSource(value = "provideRentTimeModels")
  void isTotalCostCalculatedProperly(Long amount, Long expected) {
    rentTimemodel.setAmount(amount);

    var actual = dayCalculator.calculateTotalCost(rentcostmodel, rentTimemodel);

    assertEquals(expected, actual, "calculated actual totalCost is not equal to expected");
  }
}