package com.godeltech.bikesharing.controller.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.request.EquipmentItemRequest;
import com.godeltech.bikesharing.models.response.EquipmentItemResponse;
import com.godeltech.bikesharing.service.admin.EquipmentItemManagementService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.EquipmentGroupUtils;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({EquipmentItemManagementController.class, GeneralErrorMapper.class, JsonMapper.class})
class EquipmentItemManagementControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/admin/equipment_item";
  private static final String UPDATED_NAME = "SuperEquipment";
  private static final String GROUP_CODE = EquipmentGroupUtils.CODE;
  private static final Long ID = 1L;

  private static EquipmentItemModel rentCost;
  private static EquipmentItemResponse expectedResponse;
  private static EquipmentItemRequest request;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private EquipmentItemManagementService managementService;
  @MockBean
  private EquipmentItemMapper mapper;

  @BeforeEach
  public void setUp() {
    request = EquipmentItemUtils.getEquipmentItemRequest();
    rentCost = EquipmentItemUtils.getEquipmentItemModel(ID);
    expectedResponse = EquipmentItemUtils.getEquipmentItemResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    request.setRegistrationNumber(null);

    var content = jsonMapper.getJsonRequest(request);
    mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldGetProperResponseOnDelete() throws Exception {
    var registrationNumber = request.getRegistrationNumber();

    mockMvc.perform(delete(URL_TEMPLATE + "/" + registrationNumber)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(managementService).setOutOfUse(registrationNumber);
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
    var actualResponseFromServer = jsonMapper.getResponse(result, EquipmentItemResponse.class);

    verify(managementService).saveWithGroupCode(rentCost, GROUP_CODE);
    assertEquals(expectedResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperResponseOnUpdate() throws Exception {
    request.setName(UPDATED_NAME);
    rentCost.setName(UPDATED_NAME);
    expectedResponse.setName(UPDATED_NAME);
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
    var actualResponseFromServer = jsonMapper.getResponse(result, EquipmentItemResponse.class);

    verify(managementService).update(rentCost, ID);
    assertEquals(expectedResponse, actualResponseFromServer);
  }
}