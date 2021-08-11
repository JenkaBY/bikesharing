package com.godeltech.bikesharing.models.enums;

import lombok.Getter;

@Getter
public enum ReportType {
  XLSX("xlsx"),
  CSV("csv");

  private final String suffix;

  ReportType(String suffix) {
    this.suffix = suffix;
  }
}


