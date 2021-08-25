package com.godeltech.bikesharing.service.admin.report.xlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class RowHelper {
  private final Row row;
  private final CellStyle defaultStyle;

  public RowHelper(Row row, CellStyle defaultStyle) {
    this.row = row;
    this.defaultStyle = defaultStyle;
  }

  public void createFillCell(int columnNum, String value) {
    Cell nextCell = row.createCell(columnNum);
    nextCell.setCellValue(value);
    nextCell.setCellStyle(defaultStyle);
  }

  public void createFillCell(int columnNum, Long value) {
    Cell nextCell = row.createCell(columnNum);
    nextCell.setCellValue(value);
    nextCell.setCellStyle(defaultStyle);
  }

  public void createFillCell(int columnNum, Long value, CellStyle customStyle) {
    Cell nextCell = row.createCell(columnNum);
    nextCell.setCellValue(value);
    nextCell.setCellStyle(customStyle);
  }
}
