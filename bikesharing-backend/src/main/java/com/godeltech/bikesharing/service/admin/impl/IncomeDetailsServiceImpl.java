package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.persistence.repository.RentOperationRepository;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.calculator.StartDateCalculator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncomeDetailsServiceImpl implements IncomeDetailsService {
  private final RentOperationRepository repository;
  private static final String TITLE = "Отчет по доходам за период с %s по %s";
  private static final String CSV_SEPARATOR = ",";

  @Override
  public List<IncomeDetailsItemModel> getAllIncomeDetailsItems(IncomeTimeUnit timeUnit,
                                                               LocalDate finishDateFromRequest) {
    log.info("Get income-details items for timeUnit: {} and finishDate: {} ",
        timeUnit, finishDateFromRequest);
    var finishDate = getLocalDate(finishDateFromRequest);
    var startDate = StartDateCalculator.getStartDate(timeUnit, finishDate);
    return repository.getIncomeDetails(startDate, finishDate);
  }

  private LocalDate getLocalDate(LocalDate finishDate) {
    if (finishDate == null) {
      finishDate = LocalDate.now();
    }
    return finishDate;
  }

  @Override
  public Workbook getReportXLSX(IncomeTimeUnit timeUnit, LocalDate finishDateFromRequest) {
    log.info("Get income-details-items report for timeUnit: {} and finishDate: {} ",
        timeUnit, finishDateFromRequest);
    var finishDate = getLocalDate(finishDateFromRequest);
    var startDate = StartDateCalculator.getStartDate(timeUnit, finishDate);
    var incomeDetailsItems = repository.getIncomeDetails(startDate, finishDate);

    Workbook workBook = new XSSFWorkbook();
    Sheet sheet = workBook.createSheet("Report Sheet");
    setColumnsWidth(sheet);
    setHeader(finishDate, startDate, sheet);
    var totalAmount = setContentAndGetTotalAmount(incomeDetailsItems, sheet);
    setFooter(incomeDetailsItems, sheet, totalAmount);
    return workBook;
  }

  private void setFooter(List<IncomeDetailsItemModel> incomeDetailsItems, Sheet sheet, long totalAmount) {
    Row nextRow = sheet.createRow(2 + incomeDetailsItems.size());
    nextRow.createCell(2).setCellValue("Итого:");
    nextRow.createCell(3).setCellValue(totalAmount);
  }

  private long setContentAndGetTotalAmount(List<IncomeDetailsItemModel> incomeDetailsItems, Sheet sheet) {
    var totalAmount = 0L;
    for (var item : incomeDetailsItems) {
      Row nextRow = sheet.createRow(2 + incomeDetailsItems.indexOf(item));
      nextRow.createCell(0).setCellValue(getFormattedDate(item.getDate()));
      nextRow.createCell(1).setCellValue(item.getEquipmentGroupCode());
      nextRow.createCell(2).setCellValue(item.getEquipmentRegistrationNumber());
      nextRow.createCell(3).setCellValue(item.getIncomeAmount());
      totalAmount += item.getIncomeAmount();
    }
    return totalAmount;
  }

  private String getFormattedDate(LocalDate date) {
    var formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    return date.format(formatter);
  }

  private void setHeader(LocalDate finishDate, LocalDate startDate, Sheet sheet) {
    Row row = sheet.createRow(0);
    row.createCell(0).setCellValue(String.format(TITLE, startDate, finishDate));
    Row row1 = sheet.createRow(1);
    row1.createCell(0).setCellValue("Дата");
    row1.createCell(1).setCellValue("Тип оборудования");
    row1.createCell(2).setCellValue("Номер оборудования");
    row1.createCell(3).setCellValue("Сумма выручки");
  }

  private void setColumnsWidth(Sheet sheet) {
    sheet.setColumnWidth(0, 5120);
    sheet.setColumnWidth(1, 5120);
    sheet.setColumnWidth(2, 5120);
    sheet.setColumnWidth(3, 5120);
  }

  @Override
  public String getReportCSV(IncomeTimeUnit timeUnit, LocalDate finishDateFromRequest) {

    var finishDate = getLocalDate(finishDateFromRequest);
    var startDate = StartDateCalculator.getStartDate(timeUnit, finishDate);
    var incomeDetailsItems = repository.getIncomeDetails(startDate, finishDate);
    StringBuffer sb = new StringBuffer();
    setHeader(finishDate, startDate, sb);

    var totalAmount = setContentAndGetTotalAmount(incomeDetailsItems, sb);
    setFooter(sb, " ", " ", "Итого:", String.valueOf(totalAmount));

    return  sb.toString();
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
      setFooter(sb, getFormattedDate(item.getDate()), item.getEquipmentGroupCode(),
          item.getEquipmentRegistrationNumber(), item.getIncomeAmount().toString());
      totalAmount += item.getIncomeAmount();
      sb.append(System.lineSeparator());
    }
    return totalAmount;
  }

  private void setHeader(LocalDate finishDate, LocalDate startDate, StringBuffer sb) {
    sb.append("Дата");
    nextValueInRaw(sb, "Тип оборудования");
    nextValueInRaw(sb, "Номер оборудования");
    nextValueInRaw(sb, "Сумма выручки");
    sb.append(System.lineSeparator());
  }

  private void nextValueInRaw(StringBuffer sb, String s) {
    sb.append(IncomeDetailsServiceImpl.CSV_SEPARATOR);
    sb.append(s);
  }
}
