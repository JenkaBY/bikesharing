package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.repository.EquipmentItemRepository;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.admin.EquipmentItemManagementService;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentGroupServiceImpl;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentStatusServiceImpl;
import javax.el.MethodNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentItemManagementServiceImpl implements EquipmentItemManagementService {

  private final EquipmentItemRepository repository;
  private final EquipmentGroupServiceImpl equipmentGroupService;
  private final EquipmentStatusServiceImpl equipmentStatusService;
  private final EquipmentItemService equipmentItemService;
  private final EquipmentItemMapper mapper;

  @Override
  public EquipmentItemModel saveWithGroupCode(EquipmentItemModel model, String equipmentGroupCode) {
    log.info("save: {}", model);
    var equipmentGroupModel = equipmentGroupService.getByCode(equipmentGroupCode);
    var equipmentStatusFreeCode = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE;
    var equipmentStatusModel = equipmentStatusService.getByCode(equipmentStatusFreeCode);
    var entityToBeSaved = mapper.mapToEntity(model, equipmentGroupModel, equipmentStatusModel);
    return mapper.mapToModel(repository.save(entityToBeSaved));
  }


  @Override
  public EquipmentItemModel update(EquipmentItemModel model, Long id) {
    log.info("update: {} for id: {}", model, id);
    var entityFromBase = equipmentItemService.getById(id);
    model.setId(id);
    return equipmentItemService.save(model);
  }

  @Override
  public void safeDeleteEquipmentItem(Long id) {
    throw new MethodNotFoundException("Implementation needed");
  }
}
