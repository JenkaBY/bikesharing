package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import java.time.LocalDate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface FormatReportCreator {
  StreamingResponseBody getData(IncomeTimeUnit incomeTimeUnit, LocalDate date);

  ReportFormat getFormat();
}
