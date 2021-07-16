package com.godeltech.bikesharing.models;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentOperationModel {
  public static final String INITIAL_STATUS = "LASTING";

  private Long id;
  private RentStatusModel rentStatus;
  private Long totalCost;
  private Long deposit;
  private EquipmentItemModel equipmentItem;
  private ClientAccountModel clientAccount;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String comments;
}
