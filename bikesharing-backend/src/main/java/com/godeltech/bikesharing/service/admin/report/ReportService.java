package com.godeltech.bikesharing.service.admin.report;

import com.godeltech.bikesharing.models.ReportModel;
import com.godeltech.bikesharing.models.enums.ReportType;

public interface ReportService {

  ReportType getType();

  ReportModel createPeriodicalReport();
}
