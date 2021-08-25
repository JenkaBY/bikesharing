package com.godeltech.bikesharing.service.admin.report.income.deatils;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.service.admin.report.xlsx.RowHelper;
import com.godeltech.bikesharing.service.admin.report.xlsx.WorkbookHelper;
import com.godeltech.bikesharing.service.admin.report.xlsx.XlsxReport;
import com.godeltech.bikesharing.service.util.DateUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class XlsxIncomeDetailsReport extends XlsxReport {
  private static final String SHEET_TITLE = "Income Report";
  private final List<IncomeReportHeader> headers;
  private final WorkbookHelper helper;
  private final List<IncomeDetailsItemModel> incomeDetailsItems;

  public XlsxIncomeDetailsReport(String fileName, List<IncomeDetailsItemModel> incomeDetailsItems) {
    super(fileName);
    this.headers = IncomeReportHeader.getValues();
    this.helper = new WorkbookHelper(workbook);
    this.incomeDetailsItems = incomeDetailsItems;
  }

  @Override
  protected void processSheets() {
    processForIncomeDetails(incomeDetailsItems);
  }

  private void processForIncomeDetails(List<IncomeDetailsItemModel> incomeDetailsItems) {
    workbook.createSheet(SHEET_TITLE);
    createHeader();
    createContent(incomeDetailsItems);
  }

  private void createHeader() {
    var headerValues = headers.stream()
        .collect(Collectors.toMap(IncomeReportHeader::getOrder, IncomeReportHeader::getName));
    helper.createHeader(SHEET_TITLE,headerValues);
  }

  private void createContent(List<IncomeDetailsItemModel> incomeDetailsItems) {
    Sheet sheet = workbook.getSheet(SHEET_TITLE);

    final CellStyle defaultCellStyle = helper.getDefaultStyle();
    long totalAmount = 0L;
    int rowNum = 1;
    for (IncomeDetailsItemModel item : incomeDetailsItems) {
      Row row = sheet.createRow(rowNum);
      RowHelper rowHelper = new RowHelper(row, defaultCellStyle);

      rowHelper.createFillCell(IncomeReportHeader.DATE.getOrder(), DateUtils.getFormattedDate(item.getDate()));
      rowHelper.createFillCell(IncomeReportHeader.EQUIPMENT_TYPE.getOrder(), item.getEquipmentGroupCode());
      rowHelper.createFillCell(IncomeReportHeader.EQUIPMENT_NUMBER.getOrder(), item.getEquipmentRegistrationNumber());
      long itemIncome = item.getIncomeAmount();
      rowHelper.createFillCell(IncomeReportHeader.INCOME_AMOUNT.getOrder(), itemIncome);
      totalAmount += itemIncome;
      rowNum++;
    }

    createFooter(rowNum, totalAmount);
    helper.autoSizeColumns(sheet, headers.size());
  }


  private void createFooter(int rowNum, long totalAmount) {
    Sheet sheet = workbook.getSheet(SHEET_TITLE);
    Row nextRow = sheet.createRow(rowNum);
    nextRow.createCell(2).setCellValue("Итого:");
    nextRow.createCell(3).setCellValue(totalAmount);
  }
}
