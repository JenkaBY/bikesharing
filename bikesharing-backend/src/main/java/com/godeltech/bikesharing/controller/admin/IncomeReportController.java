package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.enums.ReportFormat;
import com.godeltech.bikesharing.service.admin.report.income.IncomeReportCreator;
import com.godeltech.bikesharing.service.util.IoStreamConverter;
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
  private final IncomeReportCreator reportCreator;
  private static final String HEADER_VALUES = "attachment;filename=\"%s\"";

  @GetMapping
  public ResponseEntity<StreamingResponseBody> getReport(
      @RequestParam @NotBlank String reportFormat,
      @RequestParam @NotBlank String timeUnit,
      @RequestParam(required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    var reportFormatRequest = StringToEnumConverter.convert(reportFormat, ReportFormat.class);
    var incomeTimeUnit = StringToEnumConverter.convert(timeUnit, IncomeTimeUnit.class);

    var report = reportCreator.createIncomeReport(reportFormatRequest, incomeTimeUnit, date);
    var headerValues = String.format(HEADER_VALUES, report.getFileName());
    var content = IoStreamConverter.convertFileToStream(report.getFileContent());

    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
        .body(content);
  }
}
