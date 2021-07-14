package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RentOperationMapper {

  RentOperationModel mapToModel(RentOperation rentOperation);

  @Mapping(target = "equipmentRegistrationNumber", source = "rentOperation.equipmentItem.registrationNumber")
  @Mapping(target = "clientPhoneNumber", source = "rentOperation.clientAccount.phoneNumber")
  RentOperationResponse mapToResponse(RentOperation rentOperation);
}
