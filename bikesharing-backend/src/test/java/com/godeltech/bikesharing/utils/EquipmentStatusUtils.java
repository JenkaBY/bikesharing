package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;

public class EquipmentStatusUtils {
  public static final String NAME = "equipment is free";
  public static final String CODE = "FREE";

  public static EquipmentStatus getEquipmentStatus() {
    var equipmentStatus = new EquipmentStatus();
    equipmentStatus.setName(NAME);
    equipmentStatus.setCode(CODE);
    return equipmentStatus;
  }

  public static EquipmentStatusModel getEquipmentStatusModel() {
    var equipmentStatusModel = new EquipmentStatusModel();
    equipmentStatusModel.setName(NAME);
    equipmentStatusModel.setCode(CODE);
    return equipmentStatusModel;
  }
}
