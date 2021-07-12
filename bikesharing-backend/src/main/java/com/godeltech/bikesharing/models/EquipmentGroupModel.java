package com.godeltech.bikesharing.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentGroupModel {
  private Long id;
  private String code;
  private String name;
}
