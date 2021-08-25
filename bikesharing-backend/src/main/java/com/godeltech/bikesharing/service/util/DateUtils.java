package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class DateUtils {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public static String getFormattedDate(LocalDate date) {
    if (date != null) {
      return date.format(formatter);
    }
    return null;
  }

  public static LocalDate getDateFromString(String dateString) {
    return LocalDate.parse(dateString, formatter);
  }

  public static LocalDate getStartDate(IncomeTimeUnit timeUnit, LocalDate finishDate) {
    var timeUnitDayMonthNumbers = getMonthAndDay(finishDate);
    var year = finishDate.getYear();
    var month = timeUnitDayMonthNumbers.get(timeUnit).getMonthNum();
    var day = timeUnitDayMonthNumbers.get(timeUnit).getDayNum();
    return LocalDate.of(year, month, day);
  }

  public static LocalDate getFinishDate(LocalDate finishDate) {
    if (finishDate == null) {
      finishDate = LocalDate.now();
    }
    return finishDate;
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
