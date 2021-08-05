package com.godeltech.bikesharing.support;

import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ValidFinesAndCommentsValidator
    implements ConstraintValidator<ValidFinesAndComments, FinishRentOperationRequest> {

  @Override
  public boolean isValid(FinishRentOperationRequest value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    var violations = getViolations(value);
    if (!violations.isEmpty()) {
      putViolationsToContext(context, violations);
      return false;
    }
    return true;
  }

  private void putViolationsToContext(ConstraintValidatorContext context, List<Violation> violations) {
    context.disableDefaultConstraintViolation();
    violations.forEach(v -> context.buildConstraintViolationWithTemplate(v.getTemplate())
        .addPropertyNode(v.getProperty())
        .addConstraintViolation());
  }

  private List<Violation> getViolations(FinishRentOperationRequest value) {
    var violations = new ArrayList<Violation>();
    if ((value.getFinesAmount() != null) && (value.getComment() == null)) {
      violations.add(new Violation("comments", "Please put some comments for fines"));
    }
    if ((value.getFinesAmount() != null) && (value.getFinesAmount() <= 0)) {
      violations.add(new Violation("fines", "If fines present make sure its amount is over ZERO"));
    }
    return violations;
  }

  @RequiredArgsConstructor
  @Getter
  static class Violation {

    private final String property;
    private final String template;
  }
}
