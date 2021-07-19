package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentGroup;

public class EquipmentGroupUtils {
  public static final String NAME = "Bicycle";
  public static final String CODE = "BICYCLE";

  public static EquipmentGroup getEquipmentGroup() {
    var equipmentGroup = new EquipmentGroup();
    equipmentGroup.setName(NAME);
    equipmentGroup.setCode(CODE);
    return equipmentGroup;
  }

  public static EquipmentGroupModel getEquipmentGroupModel() {
    var equipmentGroupModel = new EquipmentGroupModel();
    equipmentGroupModel.setName(NAME);
    equipmentGroupModel.setCode(CODE);
    return equipmentGroupModel;
  }
}
