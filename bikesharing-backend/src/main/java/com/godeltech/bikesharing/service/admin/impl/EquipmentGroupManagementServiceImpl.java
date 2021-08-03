package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.persistence.repository.lookup.EquipmentGroupRepository;
import com.godeltech.bikesharing.service.admin.EquipmentGroupManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentGroupManagementServiceImpl implements EquipmentGroupManagementService {

  private final EquipmentGroupRepository repository;
  private final EquipmentGroupMapper mapper;

  @Override
  public EquipmentGroupModel save(EquipmentGroupModel model) {
    log.info("save: {}", model);
    var entityToBeSaved = mapper.mapToEntity(model);
    return mapper.mapToModel(repository.save(entityToBeSaved));
  }

  @Override
  public EquipmentGroupModel update(EquipmentGroupModel model, Long id) {
    log.info("update: {} for id: {}", model, id);
    model.setId(id);
    return save(model);
  }

}
