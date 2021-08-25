package com.godeltech.bikesharing.service.admin.report.income;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface FormatReportCreator {

  File generateFile(String fileName, List<IncomeDetailsItemModel> incomeDetails) throws Exception;

  ReportFormat getFormat();
}
