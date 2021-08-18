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
public class RequiredMaintenanceDetailsModel {
  private String equipmentGroupCode;
  private String equipmentRegistrationNumber;
  private LocalDate maintenanceDate;
  private Long timeInUse;
  private Long intervalInHours;
}
