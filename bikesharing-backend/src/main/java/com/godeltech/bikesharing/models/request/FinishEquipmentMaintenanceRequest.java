package com.godeltech.bikesharing.models.request;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FinishEquipmentMaintenanceRequest {

  @NotBlank
  private String equipmentRegistrationNumber;

  @NotNull
  private LocalDate finishedOnDate;

  private String comments;
}