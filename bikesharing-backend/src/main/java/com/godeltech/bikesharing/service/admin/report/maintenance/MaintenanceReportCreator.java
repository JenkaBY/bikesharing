package com.godeltech.bikesharing.service.admin.report.maintenance;

import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.RequiredMaintenanceDetailsModel;
import com.godeltech.bikesharing.service.maintenance.RequiredMaintenanceDetailsService;
import com.godeltech.bikesharing.service.util.DateUtils;
import com.godeltech.bikesharing.service.util.OutputStreamConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@Component
@RequiredArgsConstructor
public class MaintenanceReportCreator {
  private static final String DEFAULT = "DEFAULT";
  private static final String DANGER = "DANGER";
  private static final String WARNING = "WARNING";
  private static final String FILE_NAME = "maintenance-report.xlsx";
  private final RequiredMaintenanceDetailsService service;

  public ReportModel createReport() {
    var report = new ReportModel();
    report.setFileName(FILE_NAME);
    report.setContentType("application/octet-stream");
    report.setData(getData());
    return report;
  }

  private StreamingResponseBody getData() {
    log.info("get Required Maintenance Details XLSX-report");
    var maintenanceDetailsItems = service.getAllRequiredMaintenanceDetailsItems();
    var workBook = getWorkBook(maintenanceDetailsItems);

    return OutputStreamConverter.convertBookToStream(workBook);
  }

  private Workbook getWorkBook(List<RequiredMaintenanceDetailsModel> maintenanceDetailsModels) {
    var workBook = new XSSFWorkbook();
    var cellStyles = getCellStyles(workBook);
    var sheet = workBook.createSheet("Report Sheet");
    setColumnsWidth(sheet);
    setHeader(sheet);
    setContent(maintenanceDetailsModels, sheet, cellStyles);

    return workBook;
  }

  private Map<String, XSSFCellStyle> getCellStyles(XSSFWorkbook workBook) {
    var styles = new HashMap<String, XSSFCellStyle>();
    var styleDanger = workBook.createCellStyle();
    styleDanger.setFillForegroundColor(IndexedColors.RED.getIndex());
    styleDanger.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styles.put(DANGER, styleDanger);

    var styleWarning = workBook.createCellStyle();
    styleWarning.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    styleWarning.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styles.put(WARNING, styleWarning);

    var styleDefault = workBook.createCellStyle();
    styleDefault.setFillForegroundColor(CellStyle.NO_FILL);
    styles.put(DEFAULT, styleDefault);

    return styles;
  }

  private void setContent(List<RequiredMaintenanceDetailsModel> itemModels, Sheet sheet,
                          Map<String, XSSFCellStyle> styles) {
    for (var item : itemModels) {
      Row nextRow = sheet.createRow(1 + itemModels.indexOf(item));
      nextRow.createCell(0).setCellValue(item.getEquipmentGroupCode());
      nextRow.createCell(1).setCellValue(item.getEquipmentRegistrationNumber());
      nextRow.createCell(2).setCellValue(DateUtils.getFormattedDate(item.getMaintenanceDate()));

      var timeCell = nextRow.createCell(3);
      var style = getStyleAccordingToMaintenanceConditions(item, styles);
      timeCell.setCellStyle(style);
      timeCell.setCellValue(item.getTimeInUse());
    }
  }

  private XSSFCellStyle getStyleAccordingToMaintenanceConditions(RequiredMaintenanceDetailsModel itemModel,
                                                                 Map<String, XSSFCellStyle> style) {
    if ((itemModel.getMaintenanceDate() == null)
        && itemModel.getTimeInUse() > 0) {
      return style.get(DANGER);
    } else if (!(itemModel.getMaintenanceDate() == null)
        && ((itemModel.getTimeInUse().doubleValue()
        / itemModel.getIntervalInHours() * 100) > 10)
        && (itemModel.getTimeInUse() < itemModel.getIntervalInHours())) {
      return style.get(WARNING);
    } else if (!(itemModel.getMaintenanceDate() == null)
        && (itemModel.getTimeInUse() >= itemModel.getIntervalInHours())) {
      return style.get(DANGER);
    } else {
      return style.get(DEFAULT);
    }
  }

  private void setHeader(Sheet sheet) {
    Row row = sheet.createRow(0);
    row.createCell(0).setCellValue("Тип оборудования");
    row.createCell(1).setCellValue("Номер оборудования");
    row.createCell(2).setCellValue("Даты последнего ТО");
    row.createCell(3).setCellValue("Количество накатанных часов с даты последнего ТО");
  }

  private void setColumnsWidth(Sheet sheet) {
    sheet.setColumnWidth(0, 5120);
    sheet.setColumnWidth(1, 5120);
    sheet.setColumnWidth(2, 5120);
    sheet.setColumnWidth(3, 5120);
  }

}
