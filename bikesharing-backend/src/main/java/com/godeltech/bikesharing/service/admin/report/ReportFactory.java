package com.godeltech.bikesharing.service.admin.report;

import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.enums.ReportType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ReportFactory {
  private final Map<ReportType, ReportService> reports;

  public ReportFactory(
      List<ReportService> reports) {
    this.reports = reports.stream()
        .collect(Collectors.toMap(ReportService::getType, Function.identity()));
  }

  public ReportService getReportService(ReportType type) {
    return reports.getOrDefault(type, getDefaultReportCreator(type));
  }

  private ReportService getDefaultReportCreator(ReportType type) {
    return new ReportService() {
      @Override
      public ReportModel createReport() {
        var errorMessage = String.format("Report service is not implemented for %s ReportType", type);
        throw new RuntimeException(errorMessage);
      }

      @Override
      public ReportType getType() {
        return null;
      }
    };
  }

}
