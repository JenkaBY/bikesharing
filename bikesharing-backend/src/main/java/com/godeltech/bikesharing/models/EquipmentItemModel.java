package com.godeltech.bikesharing.models;

import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentItemModel {
  private Long id;
  private String registrationNumber;
  private String name;
  private EquipmentGroupModel equipmentGroup;
  private EquipmentStatusModel equipmentStatus;
  private String factoryNumber;
  private LocalDate purchaseDate;
  private String comments;
}
