package com.godeltech.bikesharing.support;

import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ValidNotNullCommentsValidator
    implements ConstraintValidator<ValidNotNullComments, FinishRentOperationRequest> {
  private static final List<Violation> violations = new ArrayList<>();

  @Override
  public boolean isValid(FinishRentOperationRequest value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    collectViolations(value);
    if (!violations.isEmpty()) {
      context.disableDefaultConstraintViolation();
      violations.forEach(v -> context.buildConstraintViolationWithTemplate(v.getTemplate())
          .addPropertyNode(v.getProperty())
          .addConstraintViolation());
      return false;
    }
    return true;
  }

  private void collectViolations(FinishRentOperationRequest value) {
    if (!(value.getFines() == null) && (value.getComments() == null)) {
      violations.add(new Violation("comments", "Please put some comments for fines"));

    }
    if (!(value.getFines() == null) && (value.getFines() <= 0)) {
      violations.add(new Violation("fines", "If fines present make sure its amount is over ZERO"));
    }
  }

  @RequiredArgsConstructor
  @Getter
  static class Violation {
    private final String property;
    private final String template;
  }
}