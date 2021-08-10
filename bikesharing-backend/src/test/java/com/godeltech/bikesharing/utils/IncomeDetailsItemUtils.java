package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.response.IncomeDetailsItemResponse;
import java.time.LocalDate;

public class IncomeDetailsItemUtils {
  public static final LocalDate FINISH_DATE = LocalDate.of(2021, 1, 1);
  public static final Long INCOME_AMOUNT = 4L;

  public static IncomeDetailsItemModel getIncomeDetailsItemModel() {
    var incomeDetailsItemModel = new IncomeDetailsItemModel();
    incomeDetailsItemModel.setDate(FINISH_DATE);
    incomeDetailsItemModel.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    incomeDetailsItemModel.setEquipmentGroupCode(EquipmentGroupUtils.CODE);
    incomeDetailsItemModel.setIncomeAmount(INCOME_AMOUNT);
    return incomeDetailsItemModel;
  }

  public static IncomeDetailsItemResponse getIncomeDetailsItemResponse() {
    var incomeDetailsItemResponse = new IncomeDetailsItemResponse();
    incomeDetailsItemResponse.setDate(FINISH_DATE);
    incomeDetailsItemResponse.setEquipmentRegistrationNumber(EquipmentItemUtils.REGISTRATION_NUMBER);
    incomeDetailsItemResponse.setEquipmentGroupCode(EquipmentGroupUtils.CODE);
    incomeDetailsItemResponse.setIncomeAmount(INCOME_AMOUNT);
    return incomeDetailsItemResponse;
  }
}
