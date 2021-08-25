package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.exception.ReportCreationException;
import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.admin.report.ReportService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncomeReportCreator implements ReportService {
  private static final String FILE_NAME = "report.%s";
  private final IncomeDetailsService service;
  private final IncomeDetailsReportFactory reportFactory;

  public ReportModel createIncomeReport(ReportFormat reportFormat, IncomeTimeUnit timeUnit, LocalDate finishDate) {
    var incomeDetails = service.getAllIncomeDetailsItems(timeUnit, finishDate);
    var report = new ReportModel();
    report.setFileName(String.format(FILE_NAME, reportFormat.getSuffix()));
    report.setContentType("application/octet-stream");
    try {
      report.setFileContent(reportFactory
          .getFormatReportCreator(reportFormat)
          .generateFile(FILE_NAME, incomeDetails));
    } catch (Exception e) {
      throw new ReportCreationException("File report creation failed because of: " + e.getMessage());
    }
    return report;
  }

  @Override
  public ReportType getType() {
    return ReportType.INCOME;
  }

  @Override
  public ReportModel createPeriodicalReport() {
    return createIncomeReport(ReportFormat.XLSX, IncomeTimeUnit.DAY, LocalDate.now());
  }
}
