package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.EquipmentItemModel;

public interface EquipmentItemService {
  EquipmentItemModel getByRegistrationNumber(String registrationNumber);

  EquipmentItemModel save(EquipmentItemModel equipmentItemModel);

  void setEquipmentItemStatusInUse(String registrationNumber);
}
