package com.godeltech.bikesharing.models.enums;

import lombok.Getter;

@Getter
public enum ReportFormat {
  XLSX("xlsx"),
  CSV("csv");

  private final String suffix;

  ReportFormat(String suffix) {
    this.suffix = suffix;
  }
}


