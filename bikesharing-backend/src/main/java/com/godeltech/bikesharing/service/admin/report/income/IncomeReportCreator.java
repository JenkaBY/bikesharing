package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.admin.report.ReportService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncomeReportCreator implements ReportService {
  private static final String FILE_NAME = "report.%s";
  private final IncomeDetailsReportFactory reportFactory;

  public ReportModel createIncomeReport(ReportFormat reportFormat, IncomeTimeUnit timeUnit, LocalDate finishDate) {
    var report = new ReportModel();
    report.setFileName(String.format(FILE_NAME, reportFormat.getSuffix()));
    report.setContentType("application/octet-stream");
    report.setData(reportFactory
        .getFormatReportCreator(reportFormat)
        .getData(timeUnit, finishDate));
    return report;
  }

  @Override
  public ReportType getType() {
    return ReportType.INCOME;
  }

  @Override
  public ReportModel createReport() {
    return createIncomeReport(ReportFormat.XLSX, IncomeTimeUnit.DAY, LocalDate.now());
  }
}
