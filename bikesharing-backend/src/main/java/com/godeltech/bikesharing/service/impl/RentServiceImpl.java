package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.RequestIdIsNotEqualToPathVariableException;
import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import com.godeltech.bikesharing.persistence.repository.RentOperationRepository;
import com.godeltech.bikesharing.service.ClientService;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.RentService;
import com.godeltech.bikesharing.service.calculator.RentCostTimeCalculator;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentStatusServiceImpl;
import com.godeltech.bikesharing.service.impl.lookup.RentStatusServiceImpl;
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
  private final RentCostTimeCalculator calculator;
  private final EquipmentItemService equipmentItemService;
  private final EquipmentStatusServiceImpl equipmentStatusService;
  private final ClientService clientService;
  private final RentOperationMapper rentOperationMapper;
  private final RentStatusServiceImpl rentStatusService;

  @Override
  public RentOperationModel startRentOperation(RentOperationModel rentOperation) {
    log.info("startRentOperation with model: {}", rentOperation);
    var registrationNumber = rentOperation.getEquipmentItem().getRegistrationNumber();
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(registrationNumber);

    validator.checkEquipmentItemIsFree(equipmentItemModel);
    var inUseStatusCode = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_IN_USE;
    equipmentItemModel.setEquipmentStatus(equipmentStatusService.getByCode(inUseStatusCode));
    equipmentItemService.updateEquipmentItemStatus(registrationNumber, inUseStatusCode);

    var client = clientService.getOrCreateByPhoneNumber(rentOperation.getClientAccount().getPhoneNumber());
    rentOperation.setRentStatus(rentStatusService.getByCode(RentStatusModel.RENT_STATUS_LASTING));

    var calculatedRentDetails = calculator.getCalculatedStartRentDetails(
        equipmentItemModel.getEquipmentGroup().getCode(), rentOperation.getRentTimeModel());
    var toBeSavedRentOperation = rentOperationMapper
        .mapToEntity(rentOperation, equipmentItemModel, client, calculatedRentDetails);
    var createdRentOperation = repository.save(toBeSavedRentOperation);

    return rentOperationMapper.mapToModel(createdRentOperation);
  }

  @Override
  public RentOperationModel getByEquipmentItemRegistrationNumberAndRentStatusCode(String registrationNumber,
                                                                                  String rentStatusCode) {
    log.info("getByEquipmentItemRegistrationNumberAndRentStatusCode by registrationNumber: {} and rentStatusCode: {}",
        registrationNumber, rentStatusCode);
    return repository
        .getByEquipmentItemRegistrationNumberAndRentStatusCode(registrationNumber, rentStatusCode)
        .map(rentOperationMapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(String
            .format("RentOperationModel with registrationNumber: %s and rentStatusCode: %s not found",
                registrationNumber, rentStatusCode)));
  }

  @Override
  public RentOperationModel finishRentOperation(RentOperationModel rentOperation, Long id) {
    log.info("finishRentOperation with model: {} and urlId-variable: {}", rentOperation, id);
//    FIXME You get rid of this validation when remove 'id' field from FinishRentOperationRequest
    checkIdFromModelEqualsToPathVariable(rentOperation, id);
    var rentOperationModelFromBase = getById(id);
    var registrationNumber = rentOperationModelFromBase.getEquipmentItem().getRegistrationNumber();

    var equipmentItemModel = rentOperationModelFromBase.getEquipmentItem();
    validator.checkEquipmentItemIsInUse(equipmentItemModel);
    var freeStatusCode = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE;
    equipmentItemModel.setEquipmentStatus(equipmentStatusService.getByCode(freeStatusCode));
    equipmentItemService.updateEquipmentItemStatus(registrationNumber, freeStatusCode);

    var rentStatusClosed = rentStatusService.getByCode(RentStatusModel.RENT_STATUS_CLOSED);
    rentOperationModelFromBase.setRentStatus(rentStatusClosed);

    var calculatedFinishRentDetails =
        calculator.getCalculatedFinishRentDetails(rentOperationModelFromBase, rentOperation.getFinishedAtTime());

    rentOperationModelFromBase.setFinishedAtTime(rentOperation.getFinishedAtTime());
    var toBeSavedRentOperation = rentOperationMapper
        .mapToEntity(rentOperationModelFromBase, equipmentItemModel, calculatedFinishRentDetails);
    var finishedRentOperation = repository.save(toBeSavedRentOperation);

    return rentOperationMapper.mapToModel(finishedRentOperation, calculatedFinishRentDetails);
  }

  private void checkIdFromModelEqualsToPathVariable(RentOperationModel rentOperation, Long id) {
    if (!rentOperation.getId().equals(id)) {
      throw new RequestIdIsNotEqualToPathVariableException(String
          .format("Object from request has index: %s and doesn't match index from url: %s", rentOperation.getId(), id));
    }
  }

  @Override
  @Transactional(readOnly = true)
  public RentOperationModel getById(Long id) {
    log.info("getById: {}", id);
    var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(RentOperation.class.getSimpleName(), "id", id));
    return rentOperationMapper.mapToModel(entity);
  }
}
