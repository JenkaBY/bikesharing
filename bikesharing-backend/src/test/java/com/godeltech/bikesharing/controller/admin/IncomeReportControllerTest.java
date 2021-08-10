package com.godeltech.bikesharing.controller.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.mapper.IncomeDetailsItemMapper;
import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.response.IncomeDetailsItemResponse;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.IncomeDetailsItemUtils;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({IncomeReportController.class, GeneralErrorMapper.class, JsonMapper.class})
class IncomeReportControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/admin/income";
  private static final String INCOME_TIME_UNIT = "day";
  private static final String WRONG_INCOME_TIME_UNIT = "Z-day";
  private static final LocalDate FINISH_DATE = IncomeDetailsItemUtils.FINISH_DATE;
  private static final IncomeDetailsItemModel model = IncomeDetailsItemUtils.getIncomeDetailsItemModel();
  private static final IncomeDetailsItemResponse expectedResponse =
      IncomeDetailsItemUtils.getIncomeDetailsItemResponse();

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private IncomeDetailsService service;
  @MockBean
  private IncomeDetailsItemMapper mapper;

  @Test
  public void shouldGetProperResponse() throws Exception {
    when(service.getAllIncomeDetailsItems(IncomeTimeUnit.DAY, FINISH_DATE)).thenReturn(List.of(model));
    when(mapper.mapToResponse(model))
        .thenReturn(expectedResponse);

    var result = mockMvc.perform(get(URL_TEMPLATE
        + "?timeUnit=" + INCOME_TIME_UNIT
        + "&date=" + FINISH_DATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var type = new TypeReference<List<IncomeDetailsItemResponse>>() {
    };
    var actualResponseFromServer = jsonMapper.getResponseToList(result, type);

    verify(service).getAllIncomeDetailsItems(IncomeTimeUnit.DAY, FINISH_DATE);
    assertEquals(List.of(expectedResponse), actualResponseFromServer);
  }

  @Test
  public void shouldFailWithBadRequest() throws Exception {
    when(mapper.mapToResponse(model))
        .thenReturn(expectedResponse);

    mockMvc.perform(get(URL_TEMPLATE
        + "?timeUnit=" + WRONG_INCOME_TIME_UNIT
        + "&date=" + FINISH_DATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

}