package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.service.admin.report.maintenance.MaintenanceReportCreator;
import com.godeltech.bikesharing.service.util.IoStreamConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/admin/maintenance/report")
public class MaintenanceReportController {
  private final MaintenanceReportCreator reportCreator;
  private static final String HEADER_VALUES = "attachment;filename=\"%s\"";

  @GetMapping
  public ResponseEntity<StreamingResponseBody> getReport() {

    var report = reportCreator.createPeriodicalReport();
    var headerValues = String.format(HEADER_VALUES, report.getFileName());
    var content = IoStreamConverter.convertFileToStream(report.getFileContent());

    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
        .body(content);
  }
}
