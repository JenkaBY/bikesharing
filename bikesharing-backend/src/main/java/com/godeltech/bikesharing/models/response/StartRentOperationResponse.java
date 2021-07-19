package com.godeltech.bikesharing.models.response;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StartRentOperationResponse {
  private Long id;
  private String equipmentRegistrationNumber;
  private String clientPhoneNumber;
  private Long deposit;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
}
