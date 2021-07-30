package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;

public interface EquipmentGroupManagementService {

  EquipmentGroupModel save(EquipmentGroupModel model);

  EquipmentGroupModel update(EquipmentGroupModel model, Long id);
}