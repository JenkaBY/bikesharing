package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.TimeInUseMapper;
import com.godeltech.bikesharing.models.TimeInUseModel;
import com.godeltech.bikesharing.persistence.entity.TimeInUse;
import com.godeltech.bikesharing.persistence.repository.TimeInUseRepository;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.TimeInUseService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TimeInUseServiceImpl implements TimeInUseService {
  private final TimeInUseRepository repository;
  private final TimeInUseMapper mapper;
  private final EquipmentItemService equipmentItemService;

  @Override
  public TimeInUseModel getOrCreateByEquipmentItemId(Long equipmentItemId) {
    log.info("Get existing timeInUse or create new with equipmentItemId: {}", equipmentItemId);
    var equipmentItemModel = equipmentItemService.getById(equipmentItemId);
    var timeInUse = repository.findByEquipmentItemId(equipmentItemId)
        .orElseGet(() -> repository.save(mapper.mapToEntity(equipmentItemModel,0L)));
    return mapper.mapToModel(timeInUse);
  }

  private TimeInUse getById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(TimeInUse.class.getSimpleName(), "id", id));
  }

  @Override
  public void addTimeInUse(TimeInUseModel model) {
    log.info("Add time in use by model: {}", model);
    var timeInUseModel = getOrCreateByEquipmentItemId(model.getEquipmentItem().getId());
    timeInUseModel.setMinutesInUse(timeInUseModel.getMinutesInUse() + model.getMinutesInUse());
    repository.save(mapper.mapToEntity(timeInUseModel));
  }

  @Override
  public void setToZeroTimeInUse(Long timeInUseId, LocalDate maintenanceDate) {
    log.info("Set to zero minutes in use, "
        + " set maintenanceDate: {}, for timeInUseId: {}", maintenanceDate, timeInUseId);
    var timeInUse = getById(timeInUseId);
    timeInUse.setMinutesInUse(0L);
    timeInUse.setMaintenanceDate(maintenanceDate);
    repository.save(timeInUse);
  }
}
