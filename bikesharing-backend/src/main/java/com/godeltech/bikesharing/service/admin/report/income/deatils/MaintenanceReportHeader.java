package com.godeltech.bikesharing.service.admin.report.income.deatils;

import com.godeltech.bikesharing.service.admin.report.xlsx.ReportHeader;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum MaintenanceReportHeader implements ReportHeader {
  EQUIPMENT_TYPE("Тип оборудования", 0),
  EQUIPMENT_NUMBER("Номер оборудования", 1),
  MAINTENANCE_DATE("Даты последнего ТО", 2),
  TIME_IN_USE("Количество накатанных часов с даты последнего ТО", 3);

  private final String name;
  private final int order;

  MaintenanceReportHeader(String name, int order) {
    this.name = name;
    this.order = order;
  }

  public static List<MaintenanceReportHeader> getValues() {
    return Arrays.asList(MaintenanceReportHeader.values());
  }
}
