package com.godeltech.bikesharing.models.response;

import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentMaintenanceResponse {

  private Long id;
  private String equipmentRegistrationNumber;
  private String serviceTypeCode;
  private String issueDescription;
  private LocalDate startDate;
}
