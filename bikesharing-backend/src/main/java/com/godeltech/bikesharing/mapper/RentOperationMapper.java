package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RentOperationMapper {

  RentOperationModel mapToModel(RentOperation rentOperation);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "totalCost", ignore = true)
  @Mapping(target = "startTime", ignore = true)
  @Mapping(target = "rentStatus", ignore = true)
  @Mapping(target = "endTime", ignore = true)
  @Mapping(target = "comments", ignore = true)
  @Mapping(target = "equipmentItem.registrationNumber", source = "equipmentRegistrationNumber")
  @Mapping(target = "clientAccount.phoneNumber", source = "clientPhoneNumber")
  RentOperationModel mapToModel(StartRentOperationRequest request);

  @Mapping(target = "equipmentRegistrationNumber", source = "rentOperationModel.equipmentItem.registrationNumber")
  @Mapping(target = "clientPhoneNumber", source = "rentOperationModel.clientAccount.phoneNumber")
  StartRentOperationResponse mapToResponse(RentOperationModel rentOperationModel);

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  RentOperation mapToEntity(RentOperationModel rentOperationModel);
}
