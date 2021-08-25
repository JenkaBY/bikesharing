package com.godeltech.bikesharing.service.admin.report.income.deatils;

import com.godeltech.bikesharing.models.RequiredMaintenanceDetailsModel;
import com.godeltech.bikesharing.service.admin.report.xlsx.RowHelper;
import com.godeltech.bikesharing.service.admin.report.xlsx.WorkbookHelper;
import com.godeltech.bikesharing.service.admin.report.xlsx.XlsxReport;
import com.godeltech.bikesharing.service.util.DateUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class XlsxMaintenanceDetailsReport extends XlsxReport {
  private static final String SHEET_TITLE = "Maintenance Report";
  private final List<MaintenanceReportHeader> headers;
  private final WorkbookHelper helper;
  private final List<RequiredMaintenanceDetailsModel> maintenanceDetailsItems;
  private static final String DEFAULT = "DEFAULT";
  private static final String DANGER = "DANGER";
  private static final String WARNING = "WARNING";


  public XlsxMaintenanceDetailsReport(String fileName, List<RequiredMaintenanceDetailsModel> maintenanceDetailsItems) {
    super(fileName);
    this.headers = MaintenanceReportHeader.getValues();
    this.helper = new WorkbookHelper(workbook);
    this.maintenanceDetailsItems = maintenanceDetailsItems;
  }

  @Override
  protected void processSheets() {
    processForMaintenanceDetails(maintenanceDetailsItems);
  }

  private void processForMaintenanceDetails(List<RequiredMaintenanceDetailsModel> maintenanceDetailsItems) {
    workbook.createSheet(SHEET_TITLE);
    createHeader();
    createContent(maintenanceDetailsItems);
  }

  private void createHeader() {
    var headerValues = headers.stream()
        .collect(Collectors.toMap(MaintenanceReportHeader::getOrder, MaintenanceReportHeader::getName));
    helper.createHeader(SHEET_TITLE, headerValues);
  }

  private void createContent(List<RequiredMaintenanceDetailsModel> maintenanceDetailsItems) {
    Sheet sheet = workbook.getSheet(SHEET_TITLE);
    var customStyles = getCustomCellStyles();
    final CellStyle defaultCellStyle = helper.getDefaultStyle();
    int rowNum = 1;
    for (RequiredMaintenanceDetailsModel item : maintenanceDetailsItems) {
      Row row = sheet.createRow(rowNum);
      RowHelper rowHelper = new RowHelper(row, defaultCellStyle);

      rowHelper.createFillCell(MaintenanceReportHeader.EQUIPMENT_TYPE.getOrder(),
          item.getEquipmentGroupCode());
      rowHelper.createFillCell(MaintenanceReportHeader.EQUIPMENT_NUMBER.getOrder(),
          item.getEquipmentRegistrationNumber());
      rowHelper.createFillCell(MaintenanceReportHeader.MAINTENANCE_DATE.getOrder(),
          DateUtils.getFormattedDate(item.getMaintenanceDate()));
      var style = getStyleAccordingToMaintenanceConditions(item, customStyles);
      rowHelper.createFillCell(MaintenanceReportHeader.TIME_IN_USE.getOrder(),
          item.getTimeInUse(), style);
      rowNum++;
    }

    helper.autoSizeColumns(sheet, headers.size());
  }

  private CellStyle getStyleAccordingToMaintenanceConditions(RequiredMaintenanceDetailsModel itemModel,
                                                             Map<String, CellStyle> style) {
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

  private Map<String, CellStyle> getCustomCellStyles() {
    var customStyles = new HashMap<String, CellStyle>();
    var styleDanger = workbook.createCellStyle();
    styleDanger.setFillForegroundColor(IndexedColors.RED.getIndex());
    styleDanger.setFillPattern(CellStyle.SOLID_FOREGROUND);
    customStyles.put(DANGER, styleDanger);

    var styleWarning = workbook.createCellStyle();
    styleWarning.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    styleWarning.setFillPattern(CellStyle.SOLID_FOREGROUND);
    customStyles.put(WARNING, styleWarning);

    var styleDefault = workbook.createCellStyle();
    styleDefault.setFillForegroundColor(CellStyle.NO_FILL);
    customStyles.put(DEFAULT, styleDefault);

    return customStyles;
  }
}
