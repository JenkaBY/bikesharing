package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.EquipmentItemModel;

public interface EquipmentItemManagementService {

  EquipmentItemModel saveWithGroupCode(EquipmentItemModel equipmentItemModel, String equipmentCode);

  EquipmentItemModel update(EquipmentItemModel model, Long id);

  void setOutOfUse(String registrationNumber);
}
