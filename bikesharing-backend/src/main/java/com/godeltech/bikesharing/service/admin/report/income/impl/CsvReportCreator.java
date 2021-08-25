package com.godeltech.bikesharing.service.admin.report.income.impl;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.admin.report.income.FormatReportCreator;
import com.godeltech.bikesharing.service.admin.report.income.deatils.CsvIncomeDetailsReport;
import com.godeltech.bikesharing.service.util.DateUtils;
import com.godeltech.bikesharing.service.util.IoStreamConverter;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvReportCreator implements FormatReportCreator {

  @Override
  public File generateFile(String fileName, List<IncomeDetailsItemModel> incomeDetails) throws Exception {
    log.info("generate  CSV-report file");
    return new CsvIncomeDetailsReport(fileName, incomeDetails).generate();
  }

  @Override
  public ReportFormat getFormat() {
    return ReportFormat.CSV;
  }
}
