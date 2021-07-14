package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.CreateClientAccountRequest;

public interface ClientService {

  ClientAccountModel save(CreateClientAccountRequest request);

  ClientAccountModel getByPhoneNumber(String phoneNum);

  ClientAccountModel getOrCreateByPhoneNumber(String clientPhoneNumber);
}
