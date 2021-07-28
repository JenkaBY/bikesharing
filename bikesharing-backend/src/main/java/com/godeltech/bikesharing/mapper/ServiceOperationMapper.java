package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.lookup.ServiceTypeModel;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.EquipmentMaintenanceResponse;
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
  EquipmentMaintenanceResponse mapToResponse(ServiceOperation serviceOperation);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "equipmentItem", source = "equipmentItemModel")
  @Mapping(target = "serviceType", source = "serviceType")
  ServiceOperation mapToEntity(StartEquipmentMaintenanceRequest request, EquipmentItemModel equipmentItemModel,
                               ServiceTypeModel serviceType);
}
