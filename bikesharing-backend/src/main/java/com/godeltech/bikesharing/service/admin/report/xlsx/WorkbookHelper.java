package com.godeltech.bikesharing.service.admin.report.xlsx;

import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookHelper {
  private final Workbook workbook;
  private static final short DEFAULT_FONT_SIZE = 12;
  private static final String DEFAULT_FONT = "Times New Roman";
  private final CellStyle defaultStyle;
  private Font defaultFont;

  public WorkbookHelper(Workbook workbook) {
    this.workbook = workbook;
    this.defaultStyle = createDefaultCellStyle();
  }

  public Font createFont(short fontSize, boolean isBold) {
    Font font = workbook.createFont();
    font.setFontName(DEFAULT_FONT);
    if (isBold) {
      font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    } else {
      font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
    }
    font.setFontHeightInPoints(fontSize);
    return font;
  }

  public Font createFont(boolean isBold) {
    return this.createFont(DEFAULT_FONT_SIZE, isBold);
  }

  public Font getDefaultFont() {
    if (defaultFont == null) {
      defaultFont = createFont(false);
    }
    return defaultFont;
  }

  public CellStyle createCellStyle(Font font) {
    CellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setFont(font);
    return cellStyle;
  }

  public CellStyle getDefaultStyle() {
    return this.defaultStyle;
  }

  private CellStyle createDefaultCellStyle() {
    CellStyle dateStyle = workbook.createCellStyle();
    dateStyle.setFont(getDefaultFont());
    return dateStyle;
  }

  public void autoSizeColumns(Sheet sheet, int numberOfColumns) {
    for (int colNumber = 0; colNumber < numberOfColumns; colNumber++) {
      sheet.autoSizeColumn(colNumber);
    }
  }

  public void createHeader(String sheetTitle, Map<Integer, String> values) {
    Sheet sheet = workbook.getSheet(sheetTitle);
    Row headerRow = sheet.createRow(0);
    Font headerFont = createFont(true);
    CellStyle headerCellStyle = createCellStyle(headerFont);
    RowHelper rowHelper = new RowHelper(headerRow, headerCellStyle);
    values.forEach(rowHelper::createFillCell);
  }
}