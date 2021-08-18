package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportType;
import java.time.LocalDate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface TypeReportCreator {
  StreamingResponseBody getData(IncomeTimeUnit incomeTimeUnit, LocalDate date);

  ReportType getType();
}
