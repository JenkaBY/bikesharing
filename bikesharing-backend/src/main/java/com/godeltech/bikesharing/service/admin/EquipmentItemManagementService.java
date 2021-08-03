package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.EquipmentItemModel;

public interface EquipmentItemManagementService {

  EquipmentItemModel create(EquipmentItemModel equipmentItemModel);

  EquipmentItemModel update(EquipmentItemModel model, Long id);

  void setOutOfUse(String registrationNumber);
}
