package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import com.godeltech.bikesharing.persistence.repository.RentOperationRepository;
import com.godeltech.bikesharing.service.ClientService;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.RentService;
import com.godeltech.bikesharing.service.impl.lookup.RentStatusServiceImpl;
import com.godeltech.bikesharing.service.util.FinishRentTimeCalculator;
import com.godeltech.bikesharing.service.util.RentOperationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
  private final RentOperationRepository repository;
  private final RentOperationValidator validator;
  private final FinishRentTimeCalculator calculator;
  private final EquipmentItemService equipmentItemService;
  private final ClientService clientService;
  private final RentOperationMapper rentOperationMapper;
  private final RentStatusServiceImpl rentStatusService;

  @Override
  public RentOperationModel startRentOperation(RentOperationModel rentOperationModel) {
    log.info("startRentOperation with model: {}", rentOperationModel);
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(
        rentOperationModel.getEquipmentItem().getRegistrationNumber());
    validator.checkEquipmentItemIsFree(equipmentItemModel);
    equipmentItemService.setEquipmentItemStatusInUse(rentOperationModel.getEquipmentItem().getRegistrationNumber());
    rentOperationModel.setEquipmentItem(equipmentItemModel);
    rentOperationModel.setClientAccount(clientService
        .getOrCreateByPhoneNumber(rentOperationModel.getClientAccount().getPhoneNumber()));
    rentOperationModel.setRentStatus(rentStatusService
        .getByCode(RentStatusModel.INITIAL_STATUS));

    calculator.setFinishTime(rentOperationModel);
    rentOperationModel.setTotalCost(rentOperationModel.getDeposit());

    var rentOperation = repository.save(rentOperationMapper.mapToEntity(rentOperationModel));
    return rentOperationMapper.mapToModel(rentOperation);
  }


  @Override
  @Transactional(readOnly = true)
  public RentOperationModel getById(Long id) {
    log.info("getById: {}", id);
    var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(RentOperation.class.getSimpleName(), "id", id.toString()));
    return rentOperationMapper.mapToModel(entity);
  }
}
