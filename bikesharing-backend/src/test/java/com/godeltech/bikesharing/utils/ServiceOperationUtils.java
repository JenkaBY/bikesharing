package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.request.FinishEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.FinishEquipmentMaintenanceResponse;
import com.godeltech.bikesharing.models.response.StartEquipmentMaintenanceResponse;
import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentStatus;
import java.time.LocalDate;
import liquibase.pro.packaged.E;

public class ServiceOperationUtils {

  public static final LocalDate START_DATE = LocalDate.of(2021, 1, 1);
  public static final LocalDate END_DATE = START_DATE.plusDays(1);
  public static final String ISSUE_DESCRIPTION = "something got broken";

  public static ServiceOperation getServiceOperation(Long id) {
    var serviceOperation = new ServiceOperation();
    serviceOperation.setId(id);
    serviceOperation.setServiceType(ServiceTypeUtils.getServiceType());
    serviceOperation.setEquipmentItem(EquipmentItemUtils.getEquipmentItem());
    serviceOperation.getEquipmentItem().setEquipmentStatus(getEquipmentStatusInService());
    serviceOperation.setIssueDescription(ISSUE_DESCRIPTION);
    serviceOperation.setStartDate(START_DATE);
    return serviceOperation;
  }

  private static EquipmentStatus getEquipmentStatusInService() {
    var equipmentStatus = new EquipmentStatus();
    equipmentStatus.setId(EquipmentStatusUtils.ID_FOR_SERVICE);
    equipmentStatus.setCode(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_SERVICE);
    equipmentStatus.setName("equipment been serviced");
    return equipmentStatus;
  }

  public static ServiceOperationModel getServiceOperationModel(Long id) {
    var serviceOperationModel = new ServiceOperationModel();
    serviceOperationModel.setId(id);
    serviceOperationModel.setServiceTypeModel(ServiceTypeUtils.getServiceTypeModel());
    serviceOperationModel.setEquipmentItemModel(EquipmentItemUtils.getEquipmentItemModel(id));
    serviceOperationModel.getEquipmentItemModel().setEquipmentStatus(getEquipmentStatusModelInService());
    serviceOperationModel.setIssueDescription(ISSUE_DESCRIPTION);
    serviceOperationModel.setStartDate(START_DATE);
    serviceOperationModel.setFinishedOnDate(END_DATE);
    return serviceOperationModel;
  }

  private static EquipmentStatusModel getEquipmentStatusModelInService() {
    var equipmentStatusModel = new EquipmentStatusModel();
    equipmentStatusModel.setId(EquipmentStatusUtils.ID_FOR_SERVICE);
    equipmentStatusModel.setCode(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_SERVICE);
    equipmentStatusModel.setName("equipment been serviced");
    return equipmentStatusModel;
  }

  public static StartEquipmentMaintenanceRequest getStartEquipmentMaintenanceRequest() {
    var request = new StartEquipmentMaintenanceRequest();
    request.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    request.setServiceTypeCode(ServiceTypeUtils.CODE);
    request.setIssueDescription(ISSUE_DESCRIPTION);
    return request;
  }

  public static StartEquipmentMaintenanceResponse getStartEquipmentMaintenanceResponse(Long id) {
    var equipmentMaintenanceResponse = new StartEquipmentMaintenanceResponse();
    equipmentMaintenanceResponse.setId(id);
    equipmentMaintenanceResponse.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    equipmentMaintenanceResponse.setServiceTypeCode(ServiceTypeUtils.CODE);
    equipmentMaintenanceResponse.setStartDate(START_DATE);
    equipmentMaintenanceResponse.setIssueDescription(ISSUE_DESCRIPTION);
    return equipmentMaintenanceResponse;
  }

  public static FinishEquipmentMaintenanceRequest getFinishEquipmentMaintenanceRequest() {
    var request = new FinishEquipmentMaintenanceRequest();
    request.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    request.setFinishedOnDate(END_DATE);
    return request;
  }

  public static FinishEquipmentMaintenanceResponse getFinishEquipmentMaintenanceResponse(Long id) {
    var equipmentMaintenanceResponse = new FinishEquipmentMaintenanceResponse();
    equipmentMaintenanceResponse.setId(id);
    equipmentMaintenanceResponse.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    equipmentMaintenanceResponse.setServiceTypeCode(ServiceTypeUtils.CODE);
    equipmentMaintenanceResponse.setStartDate(START_DATE);
    equipmentMaintenanceResponse.setIssueDescription(ISSUE_DESCRIPTION);
    equipmentMaintenanceResponse.setFinishedOnDate(END_DATE);
    return equipmentMaintenanceResponse;
  }
}
