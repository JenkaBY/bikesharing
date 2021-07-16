package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.CreateClientAccountRequest;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientAccountMapper {

  ClientAccountModel mapToModel(ClientAccount client);

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  ClientAccount mapToEntity(ClientAccountModel model);

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "rating", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  ClientAccount mapRequestToEntity(CreateClientAccountRequest request);
}
