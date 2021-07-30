package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ClientAccountModel;

public interface ClientService {

  ClientAccountModel getByPhoneNumber(String phoneNum);

  ClientAccountModel getOrCreateByPhoneNumber(String clientPhoneNumber);

  ClientAccountModel getById(Long id);
}
