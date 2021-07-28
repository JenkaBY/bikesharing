package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.lookup.ServiceTypeModel;
import com.godeltech.bikesharing.models.request.FinishEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.EquipmentMaintenanceResponse;
import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ServiceOperationMapper {

  @Mapping(target = "finishedOnDate", source = "serviceOperation.endDate")
  @Mapping(target = "equipmentItemModel", source = "serviceOperation.equipmentItem")
  @Mapping(target = "serviceTypeModel", source = "serviceOperation.serviceType")
  ServiceOperationModel mapToModel(ServiceOperation serviceOperation);

  @Mapping(target = "serviceTypeModel.code", source = "request.serviceTypeCode")
  @Mapping(target = "equipmentItemModel.registrationNumber", source = "request.equipmentRegistrationNumber")
  ServiceOperationModel mapToModel(StartEquipmentMaintenanceRequest request);

  @Mapping(target = "equipmentItemModel.registrationNumber", source = "request.equipmentRegistrationNumber")
  ServiceOperationModel mapToModel(FinishEquipmentMaintenanceRequest request);

  @Mapping(target = "serviceTypeCode", source = "serviceOperation.serviceType.code")
  @Mapping(target = "equipmentRegistrationNumber", source = "serviceOperation.equipmentItem.registrationNumber")
  EquipmentMaintenanceResponse mapToResponse(ServiceOperation serviceOperation);

  @Mapping(target = "comments", ignore = true)
  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "equipmentItem", source = "equipmentItemModel")
  @Mapping(target = "serviceType", source = "serviceType")
  ServiceOperation mapToEntity(ServiceOperationModel model, EquipmentItemModel equipmentItemModel,
                               ServiceTypeModel serviceType);

  @Mapping(target = "serviceType", source = "model.serviceTypeModel")
  @Mapping(target = "endDate", source = "model.finishedOnDate")
  @Mapping(target = "comments", source = "model.comments")
  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "equipmentItem", source = "equipmentItemModel")
  ServiceOperation mapToEntity(ServiceOperationModel model, EquipmentItemModel equipmentItemModel);
}
