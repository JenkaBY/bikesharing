package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.mapper.RentCostMapper;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.service.RentCostService;
import com.godeltech.bikesharing.service.admin.RentCostManagementService;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentGroupServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RentCostManagementServiceImpl implements RentCostManagementService {

  private final RentCostService rentCostService;
  private final EquipmentGroupServiceImpl equipmentGroupService;
  private final RentCostMapper mapper;

  @Override
  public RentCostModel saveWithGroupCode(RentCostModel model, String equipmentGroupCode) {
    log.info("save: {} with equipmentGroupCode {}", model, equipmentGroupCode);
    var equipmentGroupModel = equipmentGroupService.getByCode(equipmentGroupCode);
    var entityToBeSaved = mapper.mapToEntity(model, equipmentGroupModel);
    return rentCostService.save(mapper.mapToModel(entityToBeSaved));
  }

  @Override
  public RentCostModel update(RentCostModel model, Long id) {
    log.info("update: {} for id: {}", model, id);
    var entityFromBase = rentCostService.getById(id);
    model.setId(id);
    return rentCostService.save(model);
  }
}
