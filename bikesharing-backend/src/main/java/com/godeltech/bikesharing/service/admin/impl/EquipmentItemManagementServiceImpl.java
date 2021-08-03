package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.admin.EquipmentItemManagementService;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentGroupServiceImpl;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentStatusServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentItemManagementServiceImpl implements EquipmentItemManagementService {

  private final EquipmentGroupServiceImpl equipmentGroupService;
  private final EquipmentStatusServiceImpl equipmentStatusService;
  private final EquipmentItemService equipmentItemService;
  private final EquipmentItemMapper mapper;

  @Override
  public EquipmentItemModel create(EquipmentItemModel model) {
    log.info("save: {}", model);
    var equipmentGroupModel = equipmentGroupService.getByCode(model.getEquipmentGroup().getCode());
    var equipmentStatusFreeCode = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE;
    var equipmentStatusModel = equipmentStatusService.getByCode(equipmentStatusFreeCode);
    var entityToBeSaved = mapper.mapToEntity(model, equipmentGroupModel, equipmentStatusModel);
    return equipmentItemService.save(mapper.mapToModel(entityToBeSaved));
  }


  @Override
  public EquipmentItemModel update(EquipmentItemModel model, Long id) {
    log.info("update: {} for id: {}", model, id);

    var equipmentGroupModel = equipmentGroupService.getByCode(model.getEquipmentGroup().getCode());
    var equipmentStatusModel = equipmentStatusService.getByCode(model.getEquipmentStatus().getCode());
    model.setId(id);
    var entityToBeUpdated = mapper.mapToEntity(model, equipmentGroupModel, equipmentStatusModel);
    return equipmentItemService.save(mapper.mapToModel(entityToBeUpdated));
  }

  @Override
  public void setOutOfUse(String registrationNumber) {
    log.info("setOutOfUse: for registrationNumber: {}", registrationNumber);
    var equipmentCodeOutOfUse = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_BROKEN;
    equipmentItemService.updateEquipmentItemStatus(registrationNumber, equipmentCodeOutOfUse);
  }
}
