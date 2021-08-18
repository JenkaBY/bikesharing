package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportType;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Component
public class IncomeDetailsReportFactory {
  private final Map<ReportType, TypeReportCreator> reports;

  public IncomeDetailsReportFactory(
      List<TypeReportCreator> reports) {
    this.reports = reports.stream()
        .collect(Collectors.toMap(TypeReportCreator::getType, Function.identity()));
  }

  public TypeReportCreator getReportCreator(ReportType type) {
    return reports.getOrDefault(type, getDefaultReportCreator(type));
  }

  private TypeReportCreator getDefaultReportCreator(ReportType type) {
    return new TypeReportCreator() {
      @Override
      public StreamingResponseBody getData(IncomeTimeUnit incomeTimeUnit, LocalDate date) {
        var errorMessage = String.format("Income Details Report is not implemented for %s ReportType", type);
        throw new RuntimeException(errorMessage);
      }

      @Override
      public ReportType getType() {
        return null;
      }
    };
  }

}
