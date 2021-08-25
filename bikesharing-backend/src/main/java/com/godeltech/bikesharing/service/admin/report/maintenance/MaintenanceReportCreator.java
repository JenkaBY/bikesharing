package com.godeltech.bikesharing.service.admin.report.maintenance;

import com.godeltech.bikesharing.exception.ReportCreationException;
import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.RequiredMaintenanceDetailsModel;
import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.admin.report.ReportService;
import com.godeltech.bikesharing.service.admin.report.income.deatils.XlsxMaintenanceDetailsReport;
import com.godeltech.bikesharing.service.maintenance.RequiredMaintenanceDetailsService;
import java.io.File;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MaintenanceReportCreator implements ReportService {
  private static final String FILE_NAME = "maintenance-report.xlsx";
  private final RequiredMaintenanceDetailsService service;

  @Override
  public ReportType getType() {
    return ReportType.MAINTENANCE;
  }

  @Override
  public ReportModel createPeriodicalReport() {
    var maintenanceDetailsItems = service.getAllRequiredMaintenanceDetailsItems();
    var report = new ReportModel();
    report.setFileName(FILE_NAME);
    report.setContentType("application/octet-stream");
    report.setFileContent(generateFile(maintenanceDetailsItems));
    return report;
  }

  private File generateFile(List<RequiredMaintenanceDetailsModel> maintenanceDetails) {
    log.info("generate  XLSX-report file");
    try {
      return new XlsxMaintenanceDetailsReport(FILE_NAME, maintenanceDetails).generate();
    } catch (Exception e) {
      throw new ReportCreationException("File report creation failed because of: " + e.getMessage());
    }
  }

}
