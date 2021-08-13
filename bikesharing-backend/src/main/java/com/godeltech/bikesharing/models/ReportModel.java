package com.godeltech.bikesharing.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Data
@RequiredArgsConstructor
public class ReportModel {
  private String fileName;
  private String contentType;
  private StreamingResponseBody data;
}
