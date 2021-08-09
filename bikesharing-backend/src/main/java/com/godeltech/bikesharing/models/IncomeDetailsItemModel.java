package com.godeltech.bikesharing.models;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class IncomeDetailsItemModel {
  private LocalDate date;
  private String equipmentGroupCode;
  private String equipmentRegistrationNumber;
  private Long incomeAmount;
}
