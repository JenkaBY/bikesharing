package com.godeltech.bikesharing.models.response;

import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class IncomeDetailsItemResponse {
  private LocalDate date;
  private String equipmentGroupCode;
  private String equipmentRegistrationNumber;
  private Long incomeAmount;
}
