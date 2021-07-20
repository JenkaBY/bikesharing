package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.RentCostMapper;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.persistence.repository.RentCostRepository;
import com.godeltech.bikesharing.service.RentCostService;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentGroupServiceImpl;
import com.godeltech.bikesharing.service.impl.lookup.TimePeriodServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
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
  private final TimePeriodServiceImpl timePeriodService;
  private final EquipmentGroupServiceImpl equipmentGroupService;
  private final RentCostMapper mapper;

  @Override
  public List<RentCostModel> getByEquipmentGroupCode(String equipmentGroupCode) {
    var rentCostList = repository.findByEquipmentGroupCode(equipmentGroupCode);
    if (org.springframework.util.CollectionUtils.isEmpty(rentCostList)) {
      throw new ResourceNotFoundException("No cost found for equipmentGroupCode "
          + equipmentGroupCode);
    }
    return rentCostList.stream().map(mapper::mapToModel).collect(Collectors.toList());
  }

  @Override
  public RentCostModel save(RentCostModel rentCostModel) {
    //TODO check if rentCost for equipmentGroup and timePeriod exists
    rentCostModel.setTimePeriod(timePeriodService
        .getByCode(rentCostModel.getTimePeriod().getCode()));
    rentCostModel.setEquipmentGroup(equipmentGroupService
        .getByCode(rentCostModel.getEquipmentGroup().getCode()));
    var rentCost = repository.save(mapper.mapToEntity(rentCostModel));
    return mapper.mapToModel(rentCost);
  }
}
