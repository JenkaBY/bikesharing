package com.godeltech.bikesharing.service.calculator;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class StartDateCalculator {

  public static LocalDate getStartDate(IncomeTimeUnit timeUnit, LocalDate finishDate) {
    var timeUnitDayMonthNumbers = getMonthAndDay(finishDate);
    var year = finishDate.getYear();
    var month = timeUnitDayMonthNumbers.get(timeUnit).getMonthNum();
    var day = timeUnitDayMonthNumbers.get(timeUnit).getDayNum();
    return LocalDate.of(year, month, day);
  }

  private static Map<IncomeTimeUnit, MonthDayHolder> getMonthAndDay(LocalDate finishDate) {
    var counters = new HashMap<IncomeTimeUnit, MonthDayHolder>();
    counters.put(IncomeTimeUnit.YEAR, new MonthDayHolder(1, 1));
    counters.put(IncomeTimeUnit.MONTH, new MonthDayHolder(finishDate.getMonthValue(), 1));
    counters.put(IncomeTimeUnit.DAY, new MonthDayHolder(finishDate.getMonthValue(), finishDate.getDayOfMonth()));
    return counters;
  }

  @Getter
  @AllArgsConstructor
  static class MonthDayHolder {
    private final int monthNum;
    private final int dayNum;
  }
}
