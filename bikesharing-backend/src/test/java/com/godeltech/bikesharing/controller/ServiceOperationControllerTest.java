package com.godeltech.bikesharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.models.ServiceOperationModel;
import com.godeltech.bikesharing.models.request.FinishEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.EquipmentMaintenanceResponse;
import com.godeltech.bikesharing.models.response.FinishEquipmentMaintenanceResponse;
import com.godeltech.bikesharing.service.equipmentmaintenance.EquipmentMaintenanceService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({ServiceOperationController.class, GeneralErrorMapper.class, JsonMapper.class})
class ServiceOperationControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/serviceoperation/";
  private static final Long ID = 1L;
  private static final ServiceOperationModel serviceOperationModel = ServiceOperationUtils.getServiceOperationModel(ID);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private EquipmentMaintenanceService equipmentMaintenanceService;
  @MockBean
  private ServiceOperationMapper serviceOperationMapper;

  private StartEquipmentMaintenanceRequest startRequest;
  private FinishEquipmentMaintenanceRequest finishRequest;
  private EquipmentMaintenanceResponse expectedStartResponse;
  private FinishEquipmentMaintenanceResponse expectedFinishResponse;

  @BeforeEach
  public void setUp() {
    startRequest = ServiceOperationUtils.getStartEquipmentMaintenanceRequest();
    finishRequest = ServiceOperationUtils.getFinishEquipmentMaintenanceRequest();
    expectedStartResponse = ServiceOperationUtils.getStartEquipmentMaintenanceResponse(ID);
    expectedFinishResponse = ServiceOperationUtils.getFinishEquipmentMaintenanceResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    startRequest.setEquipmentRegistrationNumber("");

    var content = jsonMapper.getJsonRequest(startRequest);
    mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldGetProperStartResponse() throws Exception {
    when(serviceOperationMapper.mapToModel(startRequest)).thenReturn(serviceOperationModel);
    when(equipmentMaintenanceService.startEquipmentServiceOperation(serviceOperationModel))
        .thenReturn(serviceOperationModel);
    when(serviceOperationMapper.mapToStartResponse(serviceOperationModel))
        .thenReturn(ServiceOperationUtils.getStartEquipmentMaintenanceResponse(ID));

    var content = jsonMapper.getJsonRequest(startRequest);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, EquipmentMaintenanceResponse.class);

    verify(equipmentMaintenanceService).startEquipmentServiceOperation(serviceOperationModel);
    assertEquals(expectedStartResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperFinishResponse() throws Exception {
    when(serviceOperationMapper.mapToModel(finishRequest)).thenReturn(serviceOperationModel);
    when(equipmentMaintenanceService.finishEquipmentServiceOperation(serviceOperationModel,ID))
        .thenReturn(serviceOperationModel);
    when(serviceOperationMapper.mapToFinishResponse(serviceOperationModel))
        .thenReturn(ServiceOperationUtils.getFinishEquipmentMaintenanceResponse(ID));

    var content = jsonMapper.getJsonRequest(finishRequest);
    var result = mockMvc.perform(put(URL_TEMPLATE + ID)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, FinishEquipmentMaintenanceResponse.class);

    verify(equipmentMaintenanceService).finishEquipmentServiceOperation(serviceOperationModel,ID);
    assertEquals(expectedFinishResponse, actualResponseFromServer);
  }
}