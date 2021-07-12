package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.NewClientAccountRequest;

public interface ClientAccountService {

  ClientAccountModel save(NewClientAccountRequest request);

  ClientAccountModel getByPhoneNumber(String phoneNum);
}
