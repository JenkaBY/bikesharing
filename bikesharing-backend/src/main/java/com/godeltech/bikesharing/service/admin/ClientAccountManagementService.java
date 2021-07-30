package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.ClientAccountModel;

public interface ClientAccountManagementService {

  ClientAccountModel save(ClientAccountModel clientAccountModel);

  ClientAccountModel update(ClientAccountModel model, Long id);
}
