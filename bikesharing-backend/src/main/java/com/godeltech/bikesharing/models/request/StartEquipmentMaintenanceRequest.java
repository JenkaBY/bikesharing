package com.godeltech.bikesharing.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StartEquipmentMaintenanceRequest {
  private String equipmentRegistrationNumber;
  private String serviceTypeCode;
  private String issueDescription;
}
