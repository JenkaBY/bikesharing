package com.godeltech.bikesharing.controller.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.admin.report.income.IncomeDetailsReportFactory;
import com.godeltech.bikesharing.service.admin.report.income.IncomeReportCreator;
import com.godeltech.bikesharing.service.admin.report.income.impl.XlsxReportCreator;
import com.godeltech.bikesharing.service.util.DateUtils;
import com.godeltech.bikesharing.utils.IncomeDetailsItemUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest({IncomeReportController.class,
    IncomeReportCreator.class,
    IncomeDetailsReportFactory.class,
    XlsxReportCreator.class,
    GeneralErrorMapper.class})
class IncomeReportControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/admin/income/report";
  private static final String INCOME_TIME_UNIT = "day";
  private static final String REPORT_TYPE = "xlsx";
  private static final LocalDate FINISH_DATE = IncomeDetailsItemUtils.FINISH_DATE;
  private static final IncomeDetailsItemModel expectedModel = IncomeDetailsItemUtils.getIncomeDetailsItemModel();

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IncomeDetailsService service;

  @Test
  public void shouldGetExcelReport() throws Exception {
    when(service.getAllIncomeDetailsItems(IncomeTimeUnit.DAY, FINISH_DATE)).thenReturn(List.of(expectedModel));

    MvcResult mvcResult = mockMvc.perform(
        MockMvcRequestBuilders.get(URL_TEMPLATE
            + "?timeUnit=" + INCOME_TIME_UNIT
            + "&reportType=" + REPORT_TYPE
            + "&date=" + FINISH_DATE)
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(request().asyncStarted())
        .andReturn();
    MvcResult result = mockMvc
        .perform(asyncDispatch(mvcResult))
        .andReturn();

    var actual = getActualModelFromReport(result);

    verify(service).getAllIncomeDetailsItems(IncomeTimeUnit.DAY, FINISH_DATE);
    assertEquals(expectedModel, actual);
  }

  private IncomeDetailsItemModel getActualModelFromReport(MvcResult result) throws IOException {
    var model = new IncomeDetailsItemModel();

    byte[] workbookBytes =
        result.getResponse().getContentAsByteArray();

    Workbook actualBook = new XSSFWorkbook(new ByteArrayInputStream(workbookBytes));
    var date = DateUtils.getDateFromString(
        getCellValue(actualBook, 0).getStringCellValue());
    model.setDate(date);
    model.setEquipmentGroupCode(getCellValue(actualBook, 1).getStringCellValue());
    model.setEquipmentRegistrationNumber(getCellValue(actualBook, 2).getStringCellValue());
    var amount = getCellValue(actualBook, 3).getNumericCellValue();
    model.setIncomeAmount((long) amount);
    return model;
  }

  private Cell getCellValue(Workbook actualBook, int i) {
    return actualBook
        .getSheetAt(0)
        .getRow(1)
        .getCell(i);
  }

}