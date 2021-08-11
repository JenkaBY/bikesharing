package com.godeltech.bikesharing.models.enums;

import lombok.Getter;

@Getter
public enum IncomeTimeUnit {
  DAY("DAY"),
  MONTH("MONTH"),
  YEAR("YEAR");

  private final String name;

  IncomeTimeUnit(String name) {
    this.name = name;
  }
}
