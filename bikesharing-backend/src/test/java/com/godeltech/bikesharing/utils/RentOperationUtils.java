package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import java.time.LocalDateTime;

public class RentOperationUtils {
  public static final Long TOTAL_COST = 100L;
  public static final Long DEPOSIT = 100L;
  public static final LocalDateTime START_TIME = LocalDateTime.of(2021, 1, 1, 1, 0);
  public static final LocalDateTime END_TIME = START_TIME.plusHours(24);
  public static final RentTimeUnit TIME_UNIT_HOUR = RentTimeUnit.HOUR;
  public static final Long TIME_UNIT_AMOUNT = 1L;

  public static RentOperation getRentOperation() {
    var rentOperation = new RentOperation();
    rentOperation.setRentStatus(RentStatusUtils.getRentStatus());
    rentOperation.setEquipmentItem(EquipmentItemUtils.getEquipmentItem());
    rentOperation.setClientAccount(ClientAccountUtils.getClientAccount());
    rentOperation.setTotalCost(TOTAL_COST);
    rentOperation.setDeposit(DEPOSIT);
    rentOperation.setStartTime(START_TIME);
    rentOperation.setEndTime(END_TIME);
    return rentOperation;
  }

  public static RentOperationModel getRentOperationModel(Long id) {
    var rentOperationModel = new RentOperationModel();
    rentOperationModel.setId(id);
    rentOperationModel.setRentStatus(RentStatusUtils.getRentStatusModel());
    rentOperationModel.setEquipmentItem(EquipmentItemUtils.getEquipmentItemModel(id));
    rentOperationModel.setClientAccount(ClientAccountUtils.getClientAccountModel(id));
    rentOperationModel.setTotalCost(TOTAL_COST);
    rentOperationModel.setDeposit(DEPOSIT);
    rentOperationModel.setStartTime(START_TIME);
    rentOperationModel.setEndTime(END_TIME);
    return rentOperationModel;
  }

  public static StartRentOperationRequest getRentOperationRequest() {
    var startRentOperationRequest = new StartRentOperationRequest();
    startRentOperationRequest.setDeposit(DEPOSIT);
    startRentOperationRequest.setClientPhoneNumber(ClientAccountUtils.PHONE_NUMBER);
    startRentOperationRequest.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    startRentOperationRequest.setRentTimeRequest(RentTimeModelUtils
        .getRentTimeRequest(TIME_UNIT_HOUR,TIME_UNIT_AMOUNT));
    return startRentOperationRequest;
  }

  public static StartRentOperationResponse getRentOperationResponse(Long id) {
    var rentOperationResponse = new StartRentOperationResponse();
    rentOperationResponse.setId(id);
    rentOperationResponse.setDeposit(DEPOSIT);
    rentOperationResponse.setClientPhoneNumber(ClientAccountUtils.PHONE_NUMBER);
    rentOperationResponse.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    rentOperationResponse.setStartTime(START_TIME);
    rentOperationResponse.setEndTime(END_TIME);
    return rentOperationResponse;
  }
}
