package com.godeltech.bikesharing.support;

import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
//FIXME The class and annotation name doesn't reflect validations which they do.
public class ValidNotNullCommentsValidator
    implements ConstraintValidator<ValidNotNullComments, FinishRentOperationRequest> {
//  FIXME Please answer the question. What will happen in case a valid second and further requests after
//   the first request was invalid due to ValidNotNullComments constraint??
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
//    TODO !(value.getFines() == null) please simplify to " value.getFines() != null "
    if (!(value.getFines() == null) && (value.getComments() == null)) {
      violations.add(new Violation("comments", "Please put some comments for fines"));
    }
    //    TODO !(value.getFines() == null) please simplify to " value.getFines() != null "
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
