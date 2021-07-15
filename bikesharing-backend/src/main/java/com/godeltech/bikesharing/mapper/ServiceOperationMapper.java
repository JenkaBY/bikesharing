package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.response.EquipmentHandlingResponse;
import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ServiceOperationMapper {

  @Mapping(target = "equipmentItemModel", source = "serviceOperation.equipmentItem")
  @Mapping(target = "serviceTypeModel", source = "serviceOperation.serviceType")
  ServiceOperationModel mapToModel(ServiceOperation serviceOperation);

  @Mapping(target = "serviceTypeCode", source = "serviceOperation.serviceType.code")
  @Mapping(target = "equipmentRegistrationNumber", source = "serviceOperation.equipmentItem.registrationNumber")
  EquipmentHandlingResponse mapToResponse(ServiceOperation serviceOperation);
}