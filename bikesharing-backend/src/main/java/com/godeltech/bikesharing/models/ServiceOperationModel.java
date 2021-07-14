package com.godeltech.bikesharing.models;

import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ServiceOperationModel {
  private Long id;
  private EquipmentItemModel equipmentItemModel;
  private ServiceTypeModel serviceTypeModel;
  private String issueDescription;
  private String comments;
  private LocalDate startDate;
  private LocalDate endDate;

}
