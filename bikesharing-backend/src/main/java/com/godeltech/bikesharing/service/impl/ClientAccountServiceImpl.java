package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.NewClientAccountRequest;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import com.godeltech.bikesharing.persistence.repository.ClientAccountRepository;
import com.godeltech.bikesharing.service.ClientAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientAccountServiceImpl implements ClientAccountService {

  private final ClientAccountRepository repository;
  private final ClientAccountMapper clientAccountMapper;

  @Override
  public ClientAccountModel save(NewClientAccountRequest request) {
    log.info("save: {}", request);
    var clientAccount = clientAccountMapper.mapToEntity(request);
    clientAccount.setRating(0);
    return clientAccountMapper.mapToModel(repository.save(clientAccount));
  }

  @Override
  public ClientAccountModel getByPhoneNumber(String phoneNum) {
    log.info("getByPhoneNumber: {}", phoneNum);
    var clientAccount = repository.findByPhoneNumber(phoneNum)
        .orElseThrow(() -> new ResourceNotFoundException(ClientAccount.class.getName(), "phoneNumber", phoneNum));
    return clientAccountMapper.mapToModel(clientAccount);
  }
}
