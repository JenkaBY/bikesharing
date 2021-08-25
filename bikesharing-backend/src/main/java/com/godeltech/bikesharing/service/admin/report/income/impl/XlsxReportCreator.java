package com.godeltech.bikesharing.service.admin.report.income.impl;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import com.godeltech.bikesharing.service.admin.report.income.FormatReportCreator;
import com.godeltech.bikesharing.service.admin.report.income.deatils.XlsxIncomeDetailsReport;
import java.io.File;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class XlsxReportCreator implements FormatReportCreator {

  @Override
  public File generateFile(String fileName, List<IncomeDetailsItemModel> incomeDetails) throws Exception {
    log.info("generate  XLSX-report file");
    return new XlsxIncomeDetailsReport(fileName, incomeDetails).generate();
  }

  @Override
  public ReportFormat getFormat() {
    return ReportFormat.XLSX;
  }
}
