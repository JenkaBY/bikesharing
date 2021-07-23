package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.request.EquipmentMaintenanceRequest;
import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import com.godeltech.bikesharing.persistence.repository.ServiceOperationRepository;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.EquipmentMaintenanceService;
import com.godeltech.bikesharing.service.impl.lookup.ServiceTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentMaintenanceServiceImpl implements EquipmentMaintenanceService {
  private final ServiceOperationMapper mapper;
  private final ServiceOperationRepository repository;
  private final ServiceTypeServiceImpl serviceTypeService;
  private final EquipmentItemService equipmentItemService;

  @Override
  public ServiceOperationModel putEquipmentHandlingRequest(EquipmentMaintenanceRequest request) {
    log.info("putEquipmentHandlingRequest with request: {}", request);
    equipmentItemService.updateEquipmentItemStatus(request.getEquipmentRegistrationNumber(),
        EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_SERVICE);
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(request.getEquipmentRegistrationNumber());
    var serviceType = serviceTypeService.getByCode(request.getServiceTypeCode());
    var serviceOperation = mapper.mapToEntity(request, equipmentItemModel, serviceType);
    serviceOperation = repository.save(serviceOperation);
    return mapper.mapToModel(serviceOperation);
  }

  @Override
  @Transactional(readOnly = true)
  public ServiceOperationModel getById(Long id) {
    var serviceOperation = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ServiceOperation.class.getSimpleName(), "id", id.toString()));
    return mapper.mapToModel(serviceOperation);
  }

}
