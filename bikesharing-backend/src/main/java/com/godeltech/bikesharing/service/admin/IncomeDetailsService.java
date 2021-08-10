package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import java.time.LocalDate;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

public interface IncomeDetailsService {

  List<IncomeDetailsItemModel> getAllIncomeDetailsItems(IncomeTimeUnit timeUnit, LocalDate finishDate);

  Workbook getReportXLSX(IncomeTimeUnit incomeTimeUnit, LocalDate date);

  String getReportCSV(IncomeTimeUnit incomeTimeUnit, LocalDate date);
}
