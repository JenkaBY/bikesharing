package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ClientAccountModel;

public interface ClientService {

  ClientAccountModel getByPhoneNumber(String phoneNum);

  public ClientAccountModel save(ClientAccountModel model);

  public ClientAccountModel update(ClientAccountModel model, Long id);

  ClientAccountModel getOrCreateByPhoneNumber(String clientPhoneNumber);

  ClientAccountModel getById(Long id);
}
