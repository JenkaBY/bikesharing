package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentStatus;

public class EquipmentStatusUtils {
  public static final Long ID_FOR_SERVICE = 1L;
  public static final Long ID_FOR_IN_USE = 2L;
  public static final Long ID_FOR_FREE = 3L;
  public static final String NAME = "equipment is free";
  public static final String CODE_FREE = "FREE";

  public static EquipmentStatus getEquipmentStatus() {
    var equipmentStatus = new EquipmentStatus();
    equipmentStatus.setId(ID_FOR_FREE);
    equipmentStatus.setName(NAME);
    equipmentStatus.setCode(CODE_FREE);
    return equipmentStatus;
  }

  public static EquipmentStatusModel getEquipmentStatusModel() {
    var equipmentStatusModel = new EquipmentStatusModel();
    equipmentStatusModel.setId(ID_FOR_FREE);
    equipmentStatusModel.setName(NAME);
    equipmentStatusModel.setCode(CODE_FREE);
    return equipmentStatusModel;
  }
}