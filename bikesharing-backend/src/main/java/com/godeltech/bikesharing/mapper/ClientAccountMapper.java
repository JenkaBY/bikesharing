package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.CreateClientAccountRequest;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientAccountMapper {

  ClientAccountModel mapToModel(ClientAccount client);

  ClientAccount mapToEntity(ClientAccountModel model);

  @Mapping(target = "rating", constant = "0")
  ClientAccountModel mapRequestToModel(CreateClientAccountRequest request);
}
