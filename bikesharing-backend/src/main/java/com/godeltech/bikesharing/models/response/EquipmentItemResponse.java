package com.godeltech.bikesharing.models.response;

import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import com.godeltech.bikesharing.models.response.lookup.EquipmentStatusResponse;
import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentItemResponse {

  private Long id;
  private EquipmentGroupResponse equipmentGroup;
  private String registrationNumber;
  private String name;
  private EquipmentStatusResponse equipmentStatus;
  private String factoryNumber;
  private LocalDate purchaseDate;
  private String comments;
}
