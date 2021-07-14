package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentGroup;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import com.godeltech.bikesharing.persistence.repository.EquipmentGroupRepository;
import com.godeltech.bikesharing.persistence.repository.EquipmentItemRepository;
import com.godeltech.bikesharing.persistence.repository.EquipmentStatusRepository;
import com.godeltech.bikesharing.service.EquipmentItemService;
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
  private final EquipmentGroupRepository equipmentGroupRepository;
  private final EquipmentStatusRepository equipmentStatusRepository;
  private final EquipmentItemMapper mapper;

  @Override
  public EquipmentItemModel getByRegistrationNumber(String registrationNumber) {
    log.info("getByRegistrationNumber: {}", registrationNumber);
    var equipmentItem = repository.findByRegistrationNumber(registrationNumber)
        .orElseThrow(() -> new ResourceNotFoundException(EquipmentItem.class.getName(), "registrationNumber",
            registrationNumber));
    return mapper.mapToModel(equipmentItem);
  }

  @Override
  public EquipmentItemModel save(EquipmentItemModel model) {
    log.info("save: {}", model);
    var equipmentItem = mapper.mapToEntity(model);
    equipmentItem.setEquipmentGroup(getEquipmentGroupByCode(model.getEquipmentGroup().getCode()));
    equipmentItem.setEquipmentStatus(getEquipmentStatusByCode(model.getEquipmentStatus().getCode()));
    return mapper.mapToModel(repository.save(equipmentItem));
  }

  private EquipmentGroup getEquipmentGroupByCode(String code) {
    return equipmentGroupRepository.findByCode(code)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("EquipmentGroup not found with code: %s", code)));
  }

  private EquipmentStatus getEquipmentStatusByCode(String code) {
    return equipmentStatusRepository.findByCode(code)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("EquipmentGroup not found with code: %s", code)));
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
}
