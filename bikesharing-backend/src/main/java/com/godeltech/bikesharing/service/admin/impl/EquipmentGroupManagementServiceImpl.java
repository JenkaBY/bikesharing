package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;
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
    var clientAccountFromBase = getById(id);
    model.setId(id);
    return save(model);
  }

  @Transactional(readOnly = true)
  private EquipmentGroupModel getById(Long id) {
    log.info("getById: {}", id);
    return repository.findById(id).map(mapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(ClientAccount.class.getSimpleName(), "id", id));
  }
}
