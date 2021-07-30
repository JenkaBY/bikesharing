package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;

public interface EquipmentManagementService {

  EquipmentGroupModel saveEquipmentGroup(EquipmentGroupModel model);

  EquipmentGroupModel updateEquipmentGroup(EquipmentGroupModel model, Long id);

  // must find equipmentGroupBy code and set it for new equipmentItem
  EquipmentItemModel saveEquipmentWithGroupCode(EquipmentItemModel equipmentItemModel, String code);

  EquipmentItemModel updateEquipmentItem(EquipmentItemModel model, Long id);

  RentCostModel saveRentCostWithGroupCode(RentCostModel rentCostModel, String code);
}
