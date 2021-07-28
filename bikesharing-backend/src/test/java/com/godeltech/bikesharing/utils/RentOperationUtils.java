package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.FinishRentOperationResponse;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import java.time.LocalDateTime;

public class RentOperationUtils {
  public static final Long TOTAL_COST = 4L;
  public static final Long DEPOSIT = 4L;
  public static final Long TO_BE_REFUND_AMOUNT = 0L;
  public static final Long TO_BE_PAID_AMOUNT = 0L;
  public static final LocalDateTime START_TIME = LocalDateTime.of(2021, 1, 1, 1, 0);
  public static final LocalDateTime END_TIME = START_TIME.plusHours(1);
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
    rentOperation.setFinishedAtTime(END_TIME);
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
    rentOperationModel.setFinishedAtTime(END_TIME);
    return rentOperationModel;
  }

  public static StartRentOperationRequest getStartRentOperationRequest() {
    var startRentOperationRequest = new StartRentOperationRequest();
    startRentOperationRequest.setDeposit(DEPOSIT);
    startRentOperationRequest.setClientPhoneNumber(ClientAccountUtils.PHONE_NUMBER);
    startRentOperationRequest.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    startRentOperationRequest.setRentTimeRequest(RentTimeModelUtils
        .getRentTimeRequest(TIME_UNIT_HOUR, TIME_UNIT_AMOUNT));
    return startRentOperationRequest;
  }

  public static StartRentOperationResponse getStartRentOperationResponse(Long id) {
    var startRentOperationResponse = new StartRentOperationResponse();
    startRentOperationResponse.setId(id);
    startRentOperationResponse.setDeposit(DEPOSIT);
    startRentOperationResponse.setClientPhoneNumber(ClientAccountUtils.PHONE_NUMBER);
    startRentOperationResponse.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    startRentOperationResponse.setStartTime(START_TIME);
    startRentOperationResponse.setFinishedAtTime(END_TIME);
    return startRentOperationResponse;
  }

  public static FinishRentOperationRequest getFinishRentOperationRequest() {
    var finishRentOperationRequest = new FinishRentOperationRequest();
    finishRentOperationRequest.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    finishRentOperationRequest.setFinishedAtTime(END_TIME);
    return finishRentOperationRequest;
  }

  public static FinishRentOperationResponse getFinishRentOperationResponse(Long id) {
    var finishRentOperationResponse = new FinishRentOperationResponse();
    finishRentOperationResponse.setId(id);
    finishRentOperationResponse.setDeposit(DEPOSIT);
    finishRentOperationResponse.setClientPhoneNumber(ClientAccountUtils.PHONE_NUMBER);
    finishRentOperationResponse.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    finishRentOperationResponse.setStartTime(START_TIME);
    finishRentOperationResponse.setFinishedAtTime(END_TIME);
    finishRentOperationResponse.setToBePaidAmount(TO_BE_PAID_AMOUNT);
    finishRentOperationResponse.setToBeRefundAmount(TO_BE_REFUND_AMOUNT);
    return finishRentOperationResponse;
  }
}
