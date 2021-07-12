package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.NewClientAccountRequest;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import org.mapstruct.Mapper;

@Mapper
public interface ClientAccountMapper {

  ClientAccountModel mapToModel(ClientAccount client);

  ClientAccount mapToEntity(NewClientAccountRequest request);
}
