package com.godeltech.bikesharing.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.database.rider.core.api.dataset.DataSet;
import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class IncomeDetailsServiceTest extends AbstractIntegrationTest {
  private static final LocalDate FINISH_DATE_MONTH = LocalDate.of(2021, 01, 31);

  @Test
  @DataSet(value = {
      "/dataset/equipmentGroup/equipmentGroupAll.yml",
      "/dataset/equipmentStatus/equipmentStatusAll.yml",
      "/dataset/equipmentItem/equipmentItemInUse.yml",
      "/dataset/rentStatus/rentStatusAll.yml",
      "/dataset/rentCost/rentCostAll.yml",
      "dataset/clientAccount/clientAccountInitial.yml",
      "dataset/rentOperation/rentOperationAll.yml"},
      cleanBefore = true, useSequenceFiltering = false)
  public void shouldGetIncomeDetailsCorrectly() {

    var actualIncomeDetailsMonth =
        incomeDetailsService.getAllIncomeDetailsItems(IncomeTimeUnit.MONTH, FINISH_DATE_MONTH);
    var monthTotalIncome = (Long) actualIncomeDetailsMonth.stream()
        .mapToLong(IncomeDetailsItemModel::getIncomeAmount).sum();

    var actualIncomeDetailsYear =
        incomeDetailsService.getAllIncomeDetailsItems(IncomeTimeUnit.YEAR, null);
    var yearTotalIncome = (Long) actualIncomeDetailsYear.stream()
        .mapToLong(IncomeDetailsItemModel::getIncomeAmount).sum();
    var actualIncomeDetailsDay =
        incomeDetailsService.getAllIncomeDetailsItems(IncomeTimeUnit.DAY, FINISH_DATE_MONTH);

    assertEquals(4, actualIncomeDetailsMonth.size());
    assertEquals(32, monthTotalIncome);
    assertEquals(5, actualIncomeDetailsYear.size());
    assertEquals(36, yearTotalIncome);
    assertEquals(1, actualIncomeDetailsDay.size());

  }
}
