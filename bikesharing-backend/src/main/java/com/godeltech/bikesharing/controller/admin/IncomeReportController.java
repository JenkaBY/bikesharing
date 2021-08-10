package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.IncomeDetailsItemMapper;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.util.StringToEnumConverter;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
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
  private final IncomeDetailsService service;
  private final IncomeDetailsItemMapper mapper;


  @GetMapping
  public ResponseEntity<StreamingResponseBody> getReportXLSX(
      @RequestParam @NotBlank String timeUnit,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    var incomeTimeUnit = StringToEnumConverter.convert(timeUnit);
    var reportFile = service.getReportXLSX(incomeTimeUnit, date);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"report.xlsx\"")
        .body(reportFile::write);
  }

  @GetMapping("/csv")
  public ResponseEntity<StreamingResponseBody> getReportCSV(
      @RequestParam @NotBlank String timeUnit,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    var incomeTimeUnit = StringToEnumConverter.convert(timeUnit);
    var cont = service.getReportCSV(incomeTimeUnit, date);

    StreamingResponseBody stream = output -> {
      Writer writer = new BufferedWriter(new OutputStreamWriter(output));
      writer.write(cont);
    };

    InputStream inputStream = new ByteArrayInputStream(cont.getBytes());
    StreamingResponseBody responseBody = outputStream -> {
      int numberOfBytesToWrite;
      byte[] data = new byte[1024];
      while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
        outputStream.write(data, 0, numberOfBytesToWrite);
      }
      inputStream.close();
    };

    return ResponseEntity.status(HttpStatus.OK)
        //.contentType(MediaType.parseMediaType("application/csv"))
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"report.csv\"")
        .body(responseBody);
  }
}
