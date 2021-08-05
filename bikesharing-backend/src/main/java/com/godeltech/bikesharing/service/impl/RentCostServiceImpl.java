package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.RentCostMapper;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.persistence.entity.RentCost;
import com.godeltech.bikesharing.persistence.repository.RentCostRepository;
import com.godeltech.bikesharing.service.RentCostService;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentGroupServiceImpl;
import com.godeltech.bikesharing.service.util.RentCostValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RentCostServiceImpl implements RentCostService {

  private final RentCostRepository repository;
  private final EquipmentGroupServiceImpl equipmentGroupService;
  private final RentCostMapper mapper;
  private final RentCostValidator validator;

  @Override
  public RentCostModel getByEquipmentGroupCode(String equipmentGroupCode) {
    log.info("getByEquipmentGroupCode: {}", equipmentGroupCode);
    return repository.findByEquipmentGroupCode(equipmentGroupCode).map(mapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(RentCost.class.getSimpleName(), "equipmentGroupCode",
            equipmentGroupCode));
  }

  @Override
  public RentCostModel save(RentCostModel rentCostModel) {
    log.info("save: {}", rentCostModel);
    validator.checkRentCostExists(rentCostModel);
    rentCostModel.setEquipmentGroup(equipmentGroupService
        .getByCode(rentCostModel.getEquipmentGroup().getCode()));
    var rentCost = repository.save(mapper.mapToEntity(rentCostModel));
    return mapper.mapToModel(rentCost);
  }

  @Override
  public RentCostModel getById(Long id) {
    log.info("getById: {}", id);
    return repository.findById(id).map(mapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(RentCost.class.getSimpleName(), "id",
            id));
  }
}
