package com.godeltech.bikesharing.service.admin.report.impl;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.admin.report.ReportCreator;
import com.godeltech.bikesharing.service.util.DateUtils;
import com.godeltech.bikesharing.service.util.OutputStreamConverter;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@Component
@RequiredArgsConstructor
public class XlsxReportCreator implements ReportCreator {
  private final IncomeDetailsService service;

  @Override
  public StreamingResponseBody createReport(IncomeTimeUnit incomeTimeUnit, LocalDate date) {
    log.info("Get Income Details Items XLSX-report for timeUnit: {} and finishDate: {} ",
        incomeTimeUnit, date);
    var incomeDetailsItems = service.getAllIncomeDetailsItems(incomeTimeUnit, date);
    var workBook = getWorkBook(incomeDetailsItems);

    return OutputStreamConverter.convertBookToStream(workBook);
  }

  private Workbook getWorkBook(List<IncomeDetailsItemModel> incomeDetailsItems) {
    Workbook workBook = new XSSFWorkbook();
    Sheet sheet = workBook.createSheet("Report Sheet");
    setColumnsWidth(sheet);
    setHeader(sheet);
    var totalAmount = setContentAndGetTotalAmount(incomeDetailsItems, sheet);
    setFooter(incomeDetailsItems, sheet, totalAmount);
    return workBook;
  }

  private void setFooter(List<IncomeDetailsItemModel> incomeDetailsItems, Sheet sheet, long totalAmount) {
    Row nextRow = sheet.createRow(1 + incomeDetailsItems.size());
    nextRow.createCell(2).setCellValue("Итого:");
    nextRow.createCell(3).setCellValue(totalAmount);
  }

  private long setContentAndGetTotalAmount(List<IncomeDetailsItemModel> incomeDetailsItems, Sheet sheet) {
    var totalAmount = 0L;
    for (var item : incomeDetailsItems) {
      Row nextRow = sheet.createRow(1 + incomeDetailsItems.indexOf(item));
      nextRow.createCell(0).setCellValue(DateUtils.getFormattedDate(item.getDate()));
      nextRow.createCell(1).setCellValue(item.getEquipmentGroupCode());
      nextRow.createCell(2).setCellValue(item.getEquipmentRegistrationNumber());
      nextRow.createCell(3).setCellValue(item.getIncomeAmount());
      totalAmount += item.getIncomeAmount();
    }
    return totalAmount;
  }

  private void setHeader(Sheet sheet) {
    Row row = sheet.createRow(0);
    row.createCell(0).setCellValue("Дата");
    row.createCell(1).setCellValue("Тип оборудования");
    row.createCell(2).setCellValue("Номер оборудования");
    row.createCell(3).setCellValue("Сумма выручки");
  }

  private void setColumnsWidth(Sheet sheet) {
    sheet.setColumnWidth(0, 5120);
    sheet.setColumnWidth(1, 5120);
    sheet.setColumnWidth(2, 5120);
    sheet.setColumnWidth(3, 5120);
  }


  @Override
  public ReportType getType() {
    return ReportType.XLSX;
  }
}
