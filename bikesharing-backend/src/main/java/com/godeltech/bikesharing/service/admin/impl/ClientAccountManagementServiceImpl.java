package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.persistence.repository.ClientAccountRepository;
import com.godeltech.bikesharing.service.ClientService;
import com.godeltech.bikesharing.service.admin.ClientAccountManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClientAccountManagementServiceImpl implements ClientAccountManagementService {

  private final ClientAccountRepository repository;
  private final ClientService clientService;
  private final ClientAccountMapper mapper;

  @Override
  public ClientAccountModel save(ClientAccountModel model) {
    log.info("save: {}", model);
    var entityToBeSaved = mapper.mapToEntity(model);
    return mapper.mapToModel(repository.save(entityToBeSaved));
  }


  @Override
  public ClientAccountModel update(ClientAccountModel model, Long id) {
    log.info("update: {} for id: {}", model, id);
    var clientAccountFromBase = clientService.getById(id);
    model.setId(id);
    return save(model);
  }
}
