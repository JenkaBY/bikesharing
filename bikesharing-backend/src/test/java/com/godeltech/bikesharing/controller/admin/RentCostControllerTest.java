package com.godeltech.bikesharing.controller.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.mapper.RentCostMapper;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.request.RentCostRequest;
import com.godeltech.bikesharing.models.response.RentCostResponse;
import com.godeltech.bikesharing.service.admin.RentCostManagementService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.EquipmentGroupUtils;
import com.godeltech.bikesharing.utils.RentCostUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({RentCostManagementController.class, GeneralErrorMapper.class, JsonMapper.class})
class RentCostControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/admin/rent_cost";
  private static final Long UPDATED_PRICE = 8L;
  private static final String GROUP_CODE = EquipmentGroupUtils.CODE;
  private static final Long ID = 1L;

  private static RentCostModel rentCost;
  private static RentCostResponse expectedResponse;
  private static RentCostRequest request;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private RentCostManagementService managementService;
  @MockBean
  private RentCostMapper mapper;

  @BeforeEach
  public void setUp() {
    request = RentCostUtils.getRentCostRequest();
    rentCost = RentCostUtils.getRentCostModel(ID);
    expectedResponse = RentCostUtils.getRentCostResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    request.setHourDiscount(null);

    var content = jsonMapper.getJsonRequest(request);
    mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldGetProperResponseOnCreate() throws Exception {
    when(mapper.mapToModel(request)).thenReturn(rentCost);
    when(managementService.saveWithGroupCode(rentCost, GROUP_CODE)).thenReturn(rentCost);
    when(mapper.mapToResponse(rentCost))
        .thenReturn(expectedResponse);

    var content = jsonMapper.getJsonRequest(request);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, RentCostResponse.class);

    verify(managementService).saveWithGroupCode(rentCost, GROUP_CODE);
    assertEquals(expectedResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperResponseOnUpdate() throws Exception {
    request.setOneHourPrice(UPDATED_PRICE);
    rentCost.setOneHourPrice(UPDATED_PRICE);
    expectedResponse.setOneHourPrice(UPDATED_PRICE);
    when(mapper.mapToModel(request)).thenReturn(rentCost);
    when(managementService.update(rentCost, ID)).thenReturn(rentCost);
    when(mapper.mapToResponse(rentCost))
        .thenReturn(expectedResponse);

    var content = jsonMapper.getJsonRequest(request);
    var result = mockMvc.perform(put(URL_TEMPLATE + "/" + ID)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, RentCostResponse.class);

    verify(managementService).update(rentCost, ID);
    assertEquals(expectedResponse, actualResponseFromServer);
  }
}