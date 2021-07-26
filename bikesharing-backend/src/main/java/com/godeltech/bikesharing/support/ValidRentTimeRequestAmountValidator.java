package com.godeltech.bikesharing.support;

import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.request.RentTimeRequest;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ValidRentTimeRequestAmountValidator
    implements ConstraintValidator<ValidRentTimeRequestAmount, RentTimeRequest> {

  private static final Map<RentTimeUnit, Conditions> timeUnitMap = new HashMap<>();

  static {
    timeUnitMap
        .put(RentTimeUnit.DAY, new Conditions(1, 2, "RentTimeUnit amount for DAY should be in bounds of 1-2"));
    timeUnitMap
        .put(RentTimeUnit.HOUR, new Conditions(1, 12, "RentTimeUnit amount for HOUR should be in bounds of 1-12"));
    timeUnitMap
        .put(RentTimeUnit.HALF_HOUR, new Conditions(1, 1, "RentTimeUnit amount for HALF_HOUR should be 1"));
  }

  @Override
  public boolean isValid(RentTimeRequest value, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(timeUnitMap.get(value.getRentTimeUnit()).template);
    return value.getAmount() >= timeUnitMap.get(value.getRentTimeUnit()).minVal
        && value.getAmount() <= timeUnitMap.get(value.getRentTimeUnit()).maxVal;
  }

  @RequiredArgsConstructor
  @Getter
  static class Conditions {
    private final long minVal;
    private final long maxVal;
    private final String template;
  }
}
