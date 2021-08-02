package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.response.lookup.EquipmentStatusResponse;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentStatus;

public class EquipmentStatusUtils {
  public static final Long ID_FOR_SERVICE = 1L;
  public static final Long ID_FOR_BROKEN = 4L;
  public static final Long ID_FOR_FREE = 3L;
  public static final String NAME_FOR_FREE = "equipment is free";
  public static final String NAME_FOR_BROKEN = "equipment is out of use";

  public static EquipmentStatus getEquipmentStatusFree() {
    var equipmentStatus = new EquipmentStatus();
    equipmentStatus.setId(ID_FOR_FREE);
    equipmentStatus.setName(NAME_FOR_FREE);
    equipmentStatus.setCode(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE);
    return equipmentStatus;
  }

  public static EquipmentStatusModel getEquipmentStatusFreeModel() {
    var equipmentStatusModel = new EquipmentStatusModel();
    equipmentStatusModel.setId(ID_FOR_FREE);
    equipmentStatusModel.setName(NAME_FOR_FREE);
    equipmentStatusModel.setCode(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE);
    return equipmentStatusModel;
  }

  public static EquipmentStatusModel getEquipmentStatusBrokenModel() {
    var equipmentStatusModel = new EquipmentStatusModel();
    equipmentStatusModel.setId(ID_FOR_BROKEN);
    equipmentStatusModel.setName(NAME_FOR_BROKEN);
    equipmentStatusModel.setCode(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_BROKEN);
    return equipmentStatusModel;
  }

  public static EquipmentStatusResponse getEquipmentStatusResponse(Long id) {
    var equipmentStatus = new EquipmentStatusResponse();
    equipmentStatus.setId(id);
    equipmentStatus.setId(ID_FOR_FREE);
    equipmentStatus.setName(NAME_FOR_FREE);
    equipmentStatus.setCode(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE);
    return equipmentStatus;
  }
}