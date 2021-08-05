package com.godeltech.bikesharing.service.equipmentmaintenance.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import com.godeltech.bikesharing.persistence.repository.ServiceOperationRepository;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.equipmentmaintenance.EquipmentMaintenanceService;
import com.godeltech.bikesharing.service.impl.lookup.EquipmentStatusServiceImpl;
import com.godeltech.bikesharing.service.impl.lookup.ServiceTypeServiceImpl;
import com.godeltech.bikesharing.service.util.RentOperationValidator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentMaintenanceServiceImpl implements EquipmentMaintenanceService {

  private final ServiceOperationMapper mapper;
  private final ServiceOperationRepository repository;
  private final RentOperationValidator validator;
  private final ServiceTypeServiceImpl serviceTypeService;
  private final EquipmentStatusServiceImpl equipmentStatusService;
  private final EquipmentItemService equipmentItemService;


  @Override
  public ServiceOperationModel startEquipmentServiceOperation(ServiceOperationModel serviceOperation) {
    log.info("startEquipmentServiceOperation with model: {}", serviceOperation);
    var registrationNumber = serviceOperation.getEquipmentItemModel().getRegistrationNumber();
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(registrationNumber);

    var serviceStatusCode = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_SERVICE;
    equipmentItemService.updateEquipmentItemStatus(registrationNumber, serviceStatusCode);
    equipmentItemModel.setEquipmentStatus(equipmentStatusService.getByCode(serviceStatusCode));

    var serviceType = serviceTypeService.getByCode(serviceOperation.getServiceTypeModel().getCode());
    var toBeSavedServiceOperation = mapper.mapToEntity(serviceOperation, equipmentItemModel, serviceType);
    var createdServiceOperation = repository.save(toBeSavedServiceOperation);

    return mapper.mapToModel(createdServiceOperation);
  }

  @Override
  public ServiceOperationModel finishEquipmentServiceOperation(ServiceOperationModel serviceOperation,
      Long id) {
    log.info("finishEquipmentServiceOperation with model: {} and urlId-variable: {}", serviceOperation, id);
    var serviceOperationFromBase = getById(id);
    var registrationNumber = serviceOperationFromBase.getEquipmentItemModel().getRegistrationNumber();
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(registrationNumber);
    serviceOperationFromBase.setFinishedOnDate(serviceOperation.getFinishedOnDate());

    validator.checkEquipmentItemIsInService(equipmentItemModel);
    var isFreeStatusCode = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE;
    equipmentItemService.updateEquipmentItemStatus(registrationNumber, isFreeStatusCode);
    equipmentItemModel.setEquipmentStatus(equipmentStatusService.getByCode(isFreeStatusCode));

    var toBeSavedServiceOperation = mapper
        .mapToEntity(serviceOperationFromBase, equipmentItemModel);
    var finishedServiceOperation = repository.save(toBeSavedServiceOperation);

    return mapper.mapToModel(finishedServiceOperation);
  }

  @Override
  @Transactional(readOnly = true)
  public ServiceOperationModel getById(Long id) {
    var serviceOperation = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ServiceOperation.class.getSimpleName(), "id", id.toString()));
    return mapper.mapToModel(serviceOperation);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ServiceOperationModel> getAllUnfinished() {
    log.info("Find all unfinished serviceOperations");
    var serviceOperations = repository.findAllUnfinished();
    if (CollectionUtils.isEmpty(serviceOperations)) {
      throw new ResourceNotFoundException("No unfinished serviceOperations found");
    }
    return serviceOperations.stream()
        .map(mapper::mapToModel)
        .collect(Collectors.toList());
  }
}
