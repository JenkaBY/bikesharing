package com.godeltech.bikesharing.service.admin.report;

import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.enums.ReportType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportTypeResolver {
  private final ReportFactory factory;

  public ReportModel getReport(ReportType type) {
    return factory.getReportService(type).createPeriodicalReport();
  }
}
