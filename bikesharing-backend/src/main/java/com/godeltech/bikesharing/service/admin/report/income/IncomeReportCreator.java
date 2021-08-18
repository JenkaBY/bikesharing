package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportType;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncomeReportCreator {
  private static final String FILE_NAME = "report.%s";
  private final IncomeDetailsReportFactory reportFactory;

  public ReportModel createReport(ReportType reportType, IncomeTimeUnit timeUnit, LocalDate finishDate) {
    var report = new ReportModel();
    report.setFileName(String.format(FILE_NAME, reportType.getSuffix()));
    report.setContentType("application/octet-stream");
    report.setData(reportFactory
        .getReportCreator(reportType)
        .getData(timeUnit, finishDate));
    return report;
  }

}
