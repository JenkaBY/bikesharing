package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportType;
import com.godeltech.bikesharing.service.admin.report.IncomeDetailsReportFactory;
import com.godeltech.bikesharing.service.util.StringToEnumConverter;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/admin/income/report")
public class IncomeReportController {
  private final IncomeDetailsReportFactory factory;
  private static final String HEADER_VALUES = "attachment;filename=\"report.%s\"";

  @GetMapping
  public ResponseEntity<StreamingResponseBody> getReport(
      @RequestParam @NotBlank String reportType,
      @RequestParam @NotBlank String timeUnit,
      @RequestParam(required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    var reportTypeRequest = StringToEnumConverter.convert(reportType, ReportType.class);
    var incomeTimeUnit = StringToEnumConverter.convert(timeUnit, IncomeTimeUnit.class);
    var headerValues = String.format(HEADER_VALUES, reportTypeRequest.getSuffix());

    StreamingResponseBody responseBody = factory
        .getReportCreator(reportTypeRequest)
        .createReport(incomeTimeUnit, date);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
        .body(responseBody);
  }
}
