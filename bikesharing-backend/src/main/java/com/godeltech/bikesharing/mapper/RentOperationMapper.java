package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.RentOperationRequest;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
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
  @Mapping(target = "equipmentItem", source = "equipmentItemModel")
  @Mapping(target = "clientAccount", source = "clientAccountModel")
  RentOperationModel mapToModel(EquipmentItemModel equipmentItemModel, ClientAccountModel clientAccountModel,
                                RentOperationRequest request);

  @Mapping(target = "equipmentRegistrationNumber", source = "rentOperation.equipmentItem.registrationNumber")
  @Mapping(target = "clientPhoneNumber", source = "rentOperation.clientAccount.phoneNumber")
  RentOperationResponse mapToResponse(RentOperation rentOperation);
}
