package com.godeltech.bikesharing.service.admin.report.income.impl;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.admin.report.income.FormatReportCreator;
import com.godeltech.bikesharing.service.util.DateUtils;
import com.godeltech.bikesharing.service.util.IoStreamConverter;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvReportCreator implements FormatReportCreator {
  private final IncomeDetailsService service;
  private static final String CSV_SEPARATOR = ",";

  @Override
  public StreamingResponseBody getData(IncomeTimeUnit incomeTimeUnit, LocalDate date) {
    log.info("Get Income Details Items XLSX-report for timeUnit: {} and finishDate: {} ",
        incomeTimeUnit, date);
    var incomeDetailsItems = service.getAllIncomeDetailsItems(incomeTimeUnit, date);
    var stringReport = getStringReport(incomeDetailsItems);

    return IoStreamConverter.convertStringToStream(stringReport);
  }

  private String getStringReport(List<IncomeDetailsItemModel> incomeDetailsItems) {
    StringBuffer sb = new StringBuffer();
    setHeader(sb);
    var totalAmount = setContentAndGetTotalAmount(incomeDetailsItems, sb);
    setFooter(sb, " ", " ", "Итого:", String.valueOf(totalAmount));

    return sb.toString();
  }

  private void setFooter(StringBuffer sb, String s, String s2, String s3, String s4) {
    sb.append(s);
    nextValueInRaw(sb, s2);
    nextValueInRaw(sb, s3);
    nextValueInRaw(sb, s4);
  }

  private long setContentAndGetTotalAmount(List<IncomeDetailsItemModel> incomeDetailsItems, StringBuffer sb) {
    var totalAmount = 0L;
    for (var item : incomeDetailsItems) {
      setFooter(sb, DateUtils.getFormattedDate(item.getDate()), item.getEquipmentGroupCode(),
          item.getEquipmentRegistrationNumber(), item.getIncomeAmount().toString());
      totalAmount += item.getIncomeAmount();
      sb.append(System.lineSeparator());
    }
    return totalAmount;
  }

  private void setHeader(StringBuffer sb) {
    sb.append("Дата");
    nextValueInRaw(sb, "Тип оборудования");
    nextValueInRaw(sb, "Номер оборудования");
    nextValueInRaw(sb, "Сумма выручки");
    sb.append(System.lineSeparator());
  }

  private void nextValueInRaw(StringBuffer sb, String s) {
    sb.append(CSV_SEPARATOR);
    sb.append(s);
  }

  @Override
  public ReportFormat getFormat() {
    return ReportFormat.CSV;
  }
}
