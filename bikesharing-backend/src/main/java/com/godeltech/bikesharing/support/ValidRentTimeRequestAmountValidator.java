package com.godeltech.bikesharing.support;

import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.request.RentTimeRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidRentTimeRequestAmountValidator
    implements ConstraintValidator<ValidRentTimeRequestAmount, RentTimeRequest> {

  @Override
  public void initialize(ValidRentTimeRequestAmount constraintAnnotation) {
  }

  @Override
  public boolean isValid(RentTimeRequest value, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();
    if (value.getRentTimeUnit().equals(RentTimeUnit.DAY)) {
      context.buildConstraintViolationWithTemplate("RentTimeUnit amount for DAY should be in bounds of 1-2")
          .addConstraintViolation();
      return value.getAmount() >= 1 && value.getAmount() <= 2;
    } else if (value.getRentTimeUnit().equals(RentTimeUnit.HOUR)) {
      context.buildConstraintViolationWithTemplate("RentTimeUnit amount for HOUR should be in bounds of 1-12")
          .addConstraintViolation();
      return value.getAmount() >= 1 && value.getAmount() <= 12;
    } else if (value.getRentTimeUnit().equals(RentTimeUnit.HALF_HOUR)) {
      context.buildConstraintViolationWithTemplate("RentTimeUnit amount for HALF_HOUR should be 1")
          .addConstraintViolation();
      return value.getAmount() == 1;
    }
    return false;
  }
}
