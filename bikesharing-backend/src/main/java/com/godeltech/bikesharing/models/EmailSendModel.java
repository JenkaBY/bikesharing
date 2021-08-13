package com.godeltech.bikesharing.models;

import com.godeltech.bikesharing.models.enums.ReportType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailSendModel {
  private String recipient;
  private String sender;
  private String subject;
  private String message;
  private ReportType reportType;
}
