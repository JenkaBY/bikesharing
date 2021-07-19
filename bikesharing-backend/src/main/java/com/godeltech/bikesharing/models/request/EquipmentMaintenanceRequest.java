package com.godeltech.bikesharing.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentMaintenanceRequest {
  private String equipmentRegistrationNumber;
  private String serviceTypeCode;
  private String issueDescription;
}
