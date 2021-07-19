package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import com.godeltech.bikesharing.persistence.repository.EquipmentItemRepository;
import com.godeltech.bikesharing.service.EquipmentItemService;
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
public class EquipmentItemServiceImpl implements EquipmentItemService {
  private final EquipmentItemRepository repository;
  private final EquipmentGroupServiceImpl equipmentGroupService;
  private final EquipmentStatusServiceImpl equipmentStatusService;
  private final EquipmentItemMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public EquipmentItemModel getByRegistrationNumber(String registrationNumber) {
    log.info("getByRegistrationNumber: {}", registrationNumber);
    return repository.findByRegistrationNumber(registrationNumber).map(mapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(EquipmentItem.class.getName(), "registrationNumber",
            registrationNumber));
  }

  @Override
  public EquipmentItemModel save(EquipmentItemModel model) {
    log.info("save: {}", model);
    model.setEquipmentGroup(equipmentGroupService.getByCode(model.getEquipmentGroup().getCode()));
    model.setEquipmentStatus(equipmentStatusService.getByCode(model.getEquipmentStatus().getCode()));
    var equipmentItem = mapper.mapToEntity(model);
    return mapper.mapToModel(repository.save(equipmentItem));
  }

  @Override
  public void setEquipmentItemStatusInUse(String registrationNumber) {
    log.info("setEquipmentItemStatusInUse for registrationNumber: {}", registrationNumber);
    repository.setEquipmentItemStatusInUse(registrationNumber);
  }

  @Override
  public void setEquipmentItemStatusService(String registrationNumber) {
    log.info("setEquipmentItemStatusService for registrationNumber: {}", registrationNumber);
    repository.setEquipmentItemStatusService(registrationNumber);
  }

  @Override
  public String getEquipmentStatusCodeByRegistrationNumber(String equipmentRegistrationNumber) {
    log.info("getEquipmentItemStatusCode for registrationNumber: {}", equipmentRegistrationNumber);
    getByRegistrationNumber(equipmentRegistrationNumber);
    return repository.getEquipmentStatusCodeByRegistrationNumber(equipmentRegistrationNumber);
  }
}
