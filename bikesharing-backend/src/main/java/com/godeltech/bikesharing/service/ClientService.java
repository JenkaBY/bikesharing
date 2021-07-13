package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.NewClientAccountRequest;

public interface ClientService {

  ClientAccountModel save(NewClientAccountRequest request);

  ClientAccountModel getByPhoneNumber(String phoneNum);

  ClientAccountModel getOrCreateByPhoneNumber(String clientPhoneNumber);
}
