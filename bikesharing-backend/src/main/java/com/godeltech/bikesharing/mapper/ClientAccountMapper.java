package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.ClientAccountRequest;
import com.godeltech.bikesharing.models.response.ClientAccountResponse;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientAccountMapper {

  ClientAccountModel mapToModel(ClientAccount client);

  @Mapping(target = "rating", source = "request.rating", defaultValue = "0")
  ClientAccountModel mapToModel(ClientAccountRequest request);

  ClientAccount mapToEntity(ClientAccountModel model);

  ClientAccountResponse mapToResponse(ClientAccountModel save);
}
