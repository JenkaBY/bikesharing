package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.request.EquipmentGroupRequest;
import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentGroup;

public class EquipmentGroupUtils {
  public static final String NAME = "Bicycle";
  public static final String CODE = "BICYCLE";
  public static final Long ID = 1L;

  public static EquipmentGroup getEquipmentGroup() {
    var equipmentGroup = new EquipmentGroup();
    equipmentGroup.setId(ID);
    equipmentGroup.setName(NAME);
    equipmentGroup.setCode(CODE);
    return equipmentGroup;
  }

  public static EquipmentGroupModel getEquipmentGroupModel() {
    var equipmentGroupModel = new EquipmentGroupModel();
    equipmentGroupModel.setId(ID);
    equipmentGroupModel.setName(NAME);
    equipmentGroupModel.setCode(CODE);
    return equipmentGroupModel;
  }

  public static EquipmentGroupRequest getEquipmentGroupRequest() {
    var equipmentGroupRequest = new EquipmentGroupRequest();
    equipmentGroupRequest.setCode(CODE);
    equipmentGroupRequest.setName(NAME);
    return equipmentGroupRequest;
  }

  public static EquipmentGroupResponse getEquipmentGroupResponse(Long id) {
    var equipmentGroupResponse = new EquipmentGroupResponse();
    equipmentGroupResponse.setId(id);
    equipmentGroupResponse.setCode(CODE);
    equipmentGroupResponse.setName(NAME);
    return equipmentGroupResponse;
  }
}
