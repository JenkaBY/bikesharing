package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import com.godeltech.bikesharing.persistence.repository.ClientAccountRepository;
import com.godeltech.bikesharing.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientAccountRepository repository;
  private final ClientAccountMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public ClientAccountModel getByPhoneNumber(String phoneNum) {
    log.info("getByPhoneNumber: {}", phoneNum);
    return repository.findByPhoneNumber(phoneNum).map(mapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(ClientAccount.class.getSimpleName(), "phoneNumber", phoneNum));
  }

  @Override
  public ClientAccountModel save(ClientAccountModel model) {
    log.info("save: {}", model);
    var entityToBeSaved = mapper.mapToEntity(model);
    return mapper.mapToModel(repository.save(entityToBeSaved));
  }

  @Override
  public ClientAccountModel update(ClientAccountModel model, Long id) {
    log.info("update: {} for id: {}", model, id);
    model.setId(id);
    return save(model);
  }

  @Override
  public ClientAccountModel getOrCreateByPhoneNumber(String clientPhoneNumber) {
    log.info("getOrCreateByPhoneNumber: {}", clientPhoneNumber);
    var searchPattern = getSearchPattern(clientPhoneNumber);
    var clientAccount = repository.findByPhoneNumberEndsWith(searchPattern)
        .orElseGet(() -> createAccountByPhoneNumber(clientPhoneNumber));
    return mapper.mapToModel(clientAccount);
  }

  @Override
  public ClientAccountModel getById(Long id) {
    log.info("getById: {}", id);
    return repository.findById(id).map(mapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(ClientAccount.class.getSimpleName(), "id", id));
  }

  private ClientAccount createAccountByPhoneNumber(String clientPhoneNumber) {
    var clientAccount = new ClientAccount(clientPhoneNumber);
    repository.save(clientAccount);
    return clientAccount;
  }

  private String getSearchPattern(String clientPhoneNumber) {
    if (clientPhoneNumber.length() > 4) {
      return clientPhoneNumber.substring(clientPhoneNumber.length() - 4);
    } else {
      return clientPhoneNumber;
    }
  }

}
