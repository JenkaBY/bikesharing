package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import java.util.Arrays;

public class StringToEnumConverter {
  public static IncomeTimeUnit convert(String source) {
    try {
      return IncomeTimeUnit.valueOf(source.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(String.format("incomeTimeUnit must be one of these values %s",
          Arrays.toString(IncomeTimeUnit.values())));
    }
  }
}
