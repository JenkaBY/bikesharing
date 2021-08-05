package com.godeltech.bikesharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import com.godeltech.bikesharing.models.request.RentTimeRequest;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.FinishRentOperationResponse;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
import com.godeltech.bikesharing.service.RentService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({RentOperationController.class, GeneralErrorMapper.class, JsonMapper.class})
public class RentOperationControllerTest {

  public static final Long TO_BE_REFUND_AMOUNT = RentOperationUtils.TO_BE_REFUND_AMOUNT;
  public static final Long TO_BE_PAID_AMOUNT = RentOperationUtils.TO_BE_PAID_AMOUNT;
  private static final String URL_TEMPLATE = "/v1/bikesharing/rent_operation";
  private static final String CODE_LASTING = RentStatusModel.RENT_STATUS_LASTING;
  private static final Long ID = 1L;
  private static final RentTimeRequest WRONG_RENT_TIME_REQUEST =
      RentTimeModelUtils.getRentTimeRequest(RentTimeUnit.DAY, 3L);
  private static final RentOperationModel model = RentOperationUtils.getRentOperationModel(ID);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private RentService service;
  @MockBean
  private RentOperationMapper mapper;

  private StartRentOperationRequest startRequest;
  private FinishRentOperationRequest finishRequest;
  private RentOperationResponse expectedStartResponse;
  private FinishRentOperationResponse expectedFinishResponse;

  @BeforeEach
  public void setUp() {
    startRequest = RentOperationUtils.getStartRentOperationRequest();
    finishRequest = RentOperationUtils.getFinishRentOperationRequest();
    expectedStartResponse = RentOperationUtils.getRentOperationResponse(ID);
    expectedFinishResponse = RentOperationUtils.getFinishRentOperationResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    startRequest.setEquipmentRegistrationNumber("");
    startRequest.setRentTimeRequest(WRONG_RENT_TIME_REQUEST);

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
    when(mapper.mapToModel(startRequest)).thenReturn(model);
    when(service.startRentOperation(model)).thenReturn(model);
    when(mapper.mapToResponse(model))
        .thenReturn(RentOperationUtils.getRentOperationResponse(ID));

    var content = jsonMapper.getJsonRequest(startRequest);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, RentOperationResponse.class);

    verify(service).startRentOperation(model);
    assertEquals(expectedStartResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperFinishResponse() throws Exception {
    model.setToBeRefundAmount(TO_BE_REFUND_AMOUNT);
    model.setToBePaidAmount(TO_BE_PAID_AMOUNT);
    when(mapper.mapToModel(finishRequest)).thenReturn(model);
    when(service.finishRentOperation(model, ID)).thenReturn(model);
    when(mapper.mapToFinishResponse(model))
        .thenReturn(RentOperationUtils.getFinishRentOperationResponse(ID));

    var content = jsonMapper.getJsonRequest(finishRequest);
    var result = mockMvc.perform(put(URL_TEMPLATE + "/" + ID)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, FinishRentOperationResponse.class);

    verify(service).finishRentOperation(model, ID);
    assertEquals(expectedFinishResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperResponse() throws Exception {
    when(mapper.mapToResponse(model)).thenReturn(expectedStartResponse);
    when(service.getAllByStatusCode(CODE_LASTING)).thenReturn(List.of(model));

    var result = mockMvc.perform(get(URL_TEMPLATE + "?statusCode=" + CODE_LASTING)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var type = new TypeReference<List<RentOperationResponse>>() {
    };
    var actualResponseFromServer = jsonMapper.getResponseToList(result, type);

    verify(service).getAllByStatusCode(CODE_LASTING);
    assertEquals(List.of(expectedStartResponse), actualResponseFromServer);
  }
}
