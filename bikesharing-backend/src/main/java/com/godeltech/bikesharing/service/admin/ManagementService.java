package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;

public interface ManagementService {

  EquipmentGroupModel saveEquipmentGroup(EquipmentGroupModel model);

  EquipmentGroupModel updateEquipmentGroup(EquipmentGroupModel model, Long id);

  // must find equipmentGroupByCode and set it for new rentCost
  RentCostModel saveRentCostWithGroupCode(RentCostModel rentCostModel, String code);

  RentCostModel updateEquipmentItem(RentCostModel model, Long id);

  // must find equipmentGroupByCode and set it for new equipmentItem
  EquipmentItemModel saveEquipmentWithGroupCode(EquipmentItemModel equipmentItemModel, String code);

  EquipmentItemModel updateEquipmentItem(EquipmentItemModel model, Long id);

  void safeDeleteEquipmentItem(Long id);

  ClientAccountModel saveClientAccount(ClientAccountModel clientAccountModel);

  ClientAccountModel updateClientAccount(ClientAccountModel model, Long id);
}
