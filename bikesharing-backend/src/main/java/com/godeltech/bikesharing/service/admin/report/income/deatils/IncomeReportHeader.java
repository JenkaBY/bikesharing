package com.godeltech.bikesharing.service.admin.report.income.deatils;

import com.godeltech.bikesharing.service.admin.report.xlsx.ReportHeader;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum IncomeReportHeader implements ReportHeader {
  DATE("Дата", 0),
  EQUIPMENT_TYPE("Тип оборудования", 1),
  EQUIPMENT_NUMBER("Номер оборудования", 2),
  INCOME_AMOUNT("Сумма выручки", 3);

  private final String name;
  private final int order;

  IncomeReportHeader(String name, int order) {
    this.name = name;
    this.order = order;
  }

  public static List<IncomeReportHeader> getValues() {
    return Arrays.asList(IncomeReportHeader.values());
  }
}
