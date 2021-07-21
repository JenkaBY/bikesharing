package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.CreateClientAccountRequest;
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
  private final ClientAccountMapper clientAccountMapper;

  @Override
  public ClientAccountModel save(CreateClientAccountRequest request) {
    log.info("save: {}", request);
    var clientAccount = clientAccountMapper.mapRequestToEntity(request);
    return clientAccountMapper.mapToModel(repository.save(clientAccount));
  }

  @Override
  @Transactional(readOnly = true)
  public ClientAccountModel getByPhoneNumber(String phoneNum) {
    log.info("getByPhoneNumber: {}", phoneNum);
    return repository.findByPhoneNumber(phoneNum).map(clientAccountMapper::mapToModel)
        .orElseThrow(() -> new ResourceNotFoundException(ClientAccount.class.getSimpleName(), "phoneNumber", phoneNum));
  }

  @Override
  public ClientAccountModel getOrCreateByPhoneNumber(String clientPhoneNumber) {
    log.info("getOrCreateByPhoneNumber: {}", clientPhoneNumber);
    var searchPattern = getSearchPattern(clientPhoneNumber);
    var clientAccount = repository.findByPhoneNumberEndsWith(searchPattern)
        .orElseGet(() -> createAccountByPhoneNumber(clientPhoneNumber));
    return clientAccountMapper.mapToModel(clientAccount);
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
