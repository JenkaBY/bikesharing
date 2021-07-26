package com.godeltech.bikesharing.models;

import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentOperationModel {

  private Long id;
  private RentStatusModel rentStatus;
  private Long totalCost;
  private Long deposit;
  private EquipmentItemModel equipmentItem;
  private ClientAccountModel clientAccount;
  private LocalDateTime startTime = LocalDateTime.now();
  private LocalDateTime finishedAtTime;
  private String comments;
  private RentTimeModel rentTimeModel;

  private Long toBePaidAmount;
  private Long toBeRefundAmount;
}
