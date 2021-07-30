package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.EquipmentItemModel;

public interface EquipmentItemService {
  EquipmentItemModel getByRegistrationNumber(String registrationNumber);

  EquipmentItemModel save(EquipmentItemModel equipmentItemModel);

  EquipmentItemModel getById(Long id);

  void updateEquipmentItemStatus(String registrationNumber, String status);

  String getEquipmentStatusCodeByRegistrationNumber(String equipmentRegistrationNumber);

  //TODO get list With Status
}
