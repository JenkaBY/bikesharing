package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class IncomeDetailsReportFactory {
  private final Map<ReportFormat, FormatReportCreator> reports;

  public IncomeDetailsReportFactory(
      List<FormatReportCreator> reports) {
    this.reports = reports.stream()
        .collect(Collectors.toMap(FormatReportCreator::getFormat, Function.identity()));
  }

  public FormatReportCreator getFormatReportCreator(ReportFormat type) {
    return reports.getOrDefault(type, getDefaultReportCreator(type));
  }

  private FormatReportCreator getDefaultReportCreator(ReportFormat type) {
    return new FormatReportCreator() {
      @Override
      public File generateFile(String fileName, List<IncomeDetailsItemModel> incomeDetails) {
        var errorMessage = String.format("Income Details Report is not implemented for %s ReportType", type);
        throw new RuntimeException(errorMessage);
      }

      @Override
      public ReportFormat getFormat() {
        return null;
      }
    };
  }

}
