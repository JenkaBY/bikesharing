package com.godeltech.bikesharing.models.response;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FinishRentOperationResponse {

  private Long id;
  private String equipmentRegistrationNumber;
  private String clientPhoneNumber;
  private Long deposit;
  private Long totalCost;
  private Long toBePaidAmount;
  private Long toBeRefundAmount;
  private LocalDateTime startTime;
  private LocalDateTime finishedAtTime;
}
