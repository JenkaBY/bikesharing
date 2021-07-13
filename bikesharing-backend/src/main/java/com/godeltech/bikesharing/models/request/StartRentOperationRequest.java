package com.godeltech.bikesharing.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StartRentOperationRequest {
  private String equipmentRegistrationNumber;
  private String clientPhoneNumber;
  private Long deposit;
}
