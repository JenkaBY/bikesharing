package com.godeltech.bikesharing.models.request;

import com.godeltech.bikesharing.models.ServiceTypeModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentHandlingRequest {
  private String equipmentRegistrationNumber;
  private String serviceTypeCode;
  private String issueDescription;
}
