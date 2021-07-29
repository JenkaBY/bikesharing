package com.godeltech.bikesharing.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StartEquipmentMaintenanceRequest {
  @NotBlank
  private String equipmentRegistrationNumber;

  @NotNull
  private String serviceTypeCode;

  @NotBlank
  private String issueDescription;
}
