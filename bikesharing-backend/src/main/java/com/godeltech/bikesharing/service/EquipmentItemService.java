package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import java.util.List;

public interface EquipmentItemService {
  EquipmentItemModel getByRegistrationNumber(String registrationNumber);

  EquipmentItemModel save(EquipmentItemModel equipmentItemModel);

  EquipmentItemModel getById(Long id);

  void updateEquipmentItemStatus(String registrationNumber, String status);

  String getEquipmentStatusCodeByRegistrationNumber(String equipmentRegistrationNumber);

  List<EquipmentItemModel> getAllByStatusCode(String statusCode);
}
