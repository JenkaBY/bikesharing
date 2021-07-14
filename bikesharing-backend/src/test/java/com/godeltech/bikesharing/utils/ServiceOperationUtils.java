package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.EquipmentHandlingRequest;
import com.godeltech.bikesharing.models.response.EquipmentHandlingResponse;
import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import java.time.LocalDate;

public class ServiceOperationUtils {

  public static final LocalDate START_DATE = LocalDate.of(2021, 01, 01);
  public static final String ISSUE_DESCRIPTION = "something got broken";

  public static ServiceOperation getServiceOperation() {
    var serviceOperation = new ServiceOperation();
    serviceOperation.setServiceType(ServiceTypeUtils.getServiceType());
    serviceOperation.setEquipmentItem(EquipmentItemUtils.getEquipmentItem());
    serviceOperation.setIssueDescription(ISSUE_DESCRIPTION);
    serviceOperation.setStartDate(START_DATE);
    return serviceOperation;
  }

  public static ServiceOperationModel getServiceOperationModel(Long id) {
    var serviceOperationModel = new ServiceOperationModel();
    serviceOperationModel.setId(id);
    serviceOperationModel.setServiceTypeModel(ServiceTypeUtils.getServiceTypeModel());
    serviceOperationModel.setEquipmentItemModel(EquipmentItemUtils.getEquipmentItemModel(id));
    serviceOperationModel.setIssueDescription(ISSUE_DESCRIPTION);
    serviceOperationModel.setStartDate(START_DATE);
    return serviceOperationModel;
  }

  public static EquipmentHandlingRequest getEquipmentHandlingRequest() {
    var request = new EquipmentHandlingRequest();
    request.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    request.setServiceTypeCode(ServiceTypeUtils.CODE);
    request.setIssueDescription(ISSUE_DESCRIPTION);
    return request;
  }

  public static EquipmentHandlingResponse getEquipmentHandlingResponse(Long id) {
    var equipmentHandlingResponse = new EquipmentHandlingResponse();
    equipmentHandlingResponse.setId(id);
    equipmentHandlingResponse.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    equipmentHandlingResponse.setServiceTypeCode(ServiceTypeUtils.CODE);
    equipmentHandlingResponse.setStartDate(START_DATE);
    equipmentHandlingResponse.setIssueDescription(ISSUE_DESCRIPTION);
    return equipmentHandlingResponse;
  }
}
