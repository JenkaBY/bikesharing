package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.RentOperationRequest;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import com.godeltech.bikesharing.persistence.entity.RentStatus;
import com.godeltech.bikesharing.persistence.repository.RentOperationRepository;
import com.godeltech.bikesharing.persistence.repository.RentStatusRepository;
import com.godeltech.bikesharing.service.ClientService;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.RentService;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
  private static final String INITIAL_STATUS = "LASTING";
  private final RentOperationRepository repository;
  private final RentStatusRepository rentStatusRepository;
  private final EquipmentItemService equipmentItemService;
  private final ClientService clientService;
  private final RentOperationMapper rentOperationMapper;
  private final ClientAccountMapper clientAccountMapper;
  private final EquipmentItemMapper equipmentItemMapper;

  @Override
  public RentOperationResponse startRentOperation(@Valid RentOperationRequest request) {
    log.info("startRentOperation with request: {}", request);
    var rentOperation = new RentOperation();
    var clientModel = clientService.getOrCreateByPhoneNumber(request.getClientPhoneNumber());
    rentOperation.setClientAccount(clientAccountMapper.mapToEntity(clientModel));
    rentOperation.setDeposit(request.getDeposit());
    rentOperation.setStartTime(LocalDateTime.now());
    //TODO implement time calculator with cost of time for equipmentItem and deposit-sum
    rentOperation.setEndTime(LocalDateTime.now().plusHours(1));
    rentOperation.setTotalCost(request.getDeposit());

    equipmentItemService.setEquipmentItemStatusInUse(request.getEquipmentRegistrationNumber());
    var equipmentItemModel = equipmentItemService.getByRegistrationNumber(request.getEquipmentRegistrationNumber());
    rentOperation.setEquipmentItem(equipmentItemMapper.mapToEntity(equipmentItemModel));

    rentOperation.setRentStatus(getRentStatusByCode(INITIAL_STATUS));
    rentOperation = repository.save(rentOperation);

    return rentOperationMapper.mapToResponse(rentOperation);
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
    return rentOperationMapper.mapToModel(entity);
  }
}
