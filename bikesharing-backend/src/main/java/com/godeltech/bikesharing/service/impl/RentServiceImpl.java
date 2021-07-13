package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import com.godeltech.bikesharing.persistence.entity.RentStatus;
import com.godeltech.bikesharing.persistence.repository.RentOperationRepository;
import com.godeltech.bikesharing.persistence.repository.RentStatusRepository;
import com.godeltech.bikesharing.service.ClientService;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.RentService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
  private static final String INITIAL_STATUS = "LASTING";
  private final RentOperationRepository repository;
  private final RentStatusRepository rentStatusRepository;
  private final EquipmentItemService equipmentItemService;
  private final ClientService clientService;
  private final RentOperationMapper mapper;

  @Override
  public RentOperationModel startRentOperation(StartRentOperationRequest request) {
    log.info("startRentOperation with request: {}", request);
    var operationModel = new RentOperationModel();
    var clientModel = clientService.getOrCreateByPhoneNumber(request.getClientPhoneNumber());
    operationModel.setClientAccount(clientModel);
    operationModel.setDeposit(request.getDeposit());
    operationModel.setStartTime(LocalDateTime.now());
    //TODO implement time calculator with cost of time for equipmentItem and deposit-sum
    operationModel.setEndTime(LocalDateTime.now().plusHours(1));
    operationModel.setTotalCost(request.getDeposit());

    equipmentItemService.setEquipmentItemStatusInUse(request.getEquipmentRegistrationNumber());
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(request.getEquipmentRegistrationNumber());
    operationModel.setEquipmentItem(equipmentItemModel);

    var rentOperation = mapper.mapToEntity(operationModel);
    rentOperation.setRentStatus(getRentStatusByCode(INITIAL_STATUS));
    rentOperation = repository.save(rentOperation);

    return mapper.mapToModel(rentOperation);
  }

  private RentStatus getRentStatusByCode(String code) {
    return rentStatusRepository.findByCode(code)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("Rent status not found with code: %s", INITIAL_STATUS)));
  }

  @Override
  public RentOperationModel getById(Long id) {
    log.info("getById: {}", id);
    var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(RentOperation.class.getName(), "id", id.toString()));
    return mapper.mapToModel(entity);
  }
}
