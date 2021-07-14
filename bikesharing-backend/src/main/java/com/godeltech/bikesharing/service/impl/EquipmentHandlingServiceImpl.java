package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.EquipmentHandlingRequest;
import com.godeltech.bikesharing.persistence.entity.ServiceType;
import com.godeltech.bikesharing.persistence.repository.ServiceOperationRepository;
import com.godeltech.bikesharing.persistence.repository.ServiceTypeRepository;
import com.godeltech.bikesharing.service.EquipmentHandlingService;
import com.godeltech.bikesharing.service.EquipmentItemService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentHandlingServiceImpl implements EquipmentHandlingService {
  private final ServiceOperationMapper mapper;
  private final ServiceOperationRepository repository;
  private final ServiceTypeRepository serviceTypeRepository;
  private final EquipmentItemService equipmentItemService;

  @Override
  public ServiceOperationModel putEquipmentHandlingRequest(EquipmentHandlingRequest request) {
    log.info("putEquipmentHandlingRequest with request: {}", request);
    var operationModel = new ServiceOperationModel();
    operationModel.setStartDate(LocalDate.now());
    operationModel.setIssueDescription(request.getIssueDescription());

    equipmentItemService.setEquipmentItemStatusService(request.getEquipmentRegistrationNumber());
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(request.getEquipmentRegistrationNumber());
    operationModel.setEquipmentItemModel(equipmentItemModel);

    var serviceOperation = mapper.mapToEntity(operationModel);
    var serviceType = getServiceTypeByCode(request.getServiceTypeCode());
    serviceOperation.setServiceType(serviceType);
    serviceOperation = repository.save(serviceOperation);

    return mapper.mapToModel(serviceOperation);
  }

  private ServiceType getServiceTypeByCode(String code) {
    return serviceTypeRepository.findByCode(code)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("Rent status not found with code: %s", code)));
  }

}
