package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.CalculatedRentDetailsModel;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RentOperationMapper {

  RentOperationModel mapToModel(RentOperation rentOperation);

  @Mapping(target = "equipmentItem.registrationNumber", source = "equipmentRegistrationNumber")
  @Mapping(target = "clientAccount.phoneNumber", source = "clientPhoneNumber")
  RentOperationModel mapToModel(StartRentOperationRequest request);

  @Mapping(target = "equipmentRegistrationNumber", source = "rentOperationModel.equipmentItem.registrationNumber")
  @Mapping(target = "clientPhoneNumber", source = "rentOperationModel.clientAccount.phoneNumber")
  StartRentOperationResponse mapToResponse(RentOperationModel rentOperationModel);

  RentOperation mapToEntity(RentOperationModel rentOperationModel);

  @Mapping(target = "id", source = "rentOperationModel.comments")
  @Mapping(target = "comments", ignore = true)
  @Mapping(target = "equipmentItem", source = "equipmentItemModel")
  @Mapping(target = "clientAccount", source = "clientAccountModel")
  @Mapping(target = "totalCost", source = "calculatedRentDetailsModel.totalCost")
  @Mapping(target = "endTime",
      expression = "java(rentOperationModel.getStartTime().plusMinutes(calculatedRentDetailsModel.getPaidMinutes()))")
  RentOperation mapToEntity(RentOperationModel rentOperationModel, EquipmentItemModel equipmentItemModel,
                            ClientAccountModel clientAccountModel,
                            CalculatedRentDetailsModel calculatedRentDetailsModel);
}
