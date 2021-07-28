package com.godeltech.bikesharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import com.godeltech.bikesharing.models.request.RentTimeRequest;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.FinishRentOperationResponse;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.service.RentService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({RentOperationController.class, GeneralErrorMapper.class, JsonMapper.class})
public class RentOperationControllerTest {
  private static final String URL_TEMPLATE_START = "/v1/bikesharing/rentoperation/start";
  private static final String URL_TEMPLATE_FINISH = "/v1/bikesharing/rentoperation/finish";
  private static final Long ID = 1L;
  private static final RentTimeRequest WRONG_RENT_TIME_REQUEST =
      RentTimeModelUtils.getRentTimeRequest(RentTimeUnit.DAY, 3L);
  public static final Long TO_BE_REFUND_AMOUNT = RentOperationUtils.TO_BE_REFUND_AMOUNT;
  public static final Long TO_BE_PAID_AMOUNT = RentOperationUtils.TO_BE_PAID_AMOUNT;
  private static final RentOperationModel rentOperationModel = RentOperationUtils.getRentOperationModel(ID);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private RentService rentService;
  @MockBean
  private RentOperationMapper rentOperationMapper;

  private StartRentOperationRequest startRequest;
  private FinishRentOperationRequest finishRequest;
  private StartRentOperationResponse expectedStartResponse;
  private FinishRentOperationResponse expectedFinishResponse;

  @BeforeEach
  public void setUp() {
    startRequest = RentOperationUtils.getStartRentOperationRequest();
    finishRequest = RentOperationUtils.getFinishRentOperationRequest();
    expectedStartResponse = RentOperationUtils.getStartRentOperationResponse(ID);
    expectedFinishResponse = RentOperationUtils.getFinishRentOperationResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    startRequest.setEquipmentRegistrationNumber("");
    startRequest.setRentTimeRequest(WRONG_RENT_TIME_REQUEST);

    var content = jsonMapper.getJsonRequest(startRequest);
    mockMvc.perform(post(URL_TEMPLATE_START)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldGetProperStartResponse() throws Exception {
    when(rentOperationMapper.mapToModel(startRequest)).thenReturn(rentOperationModel);
    when(rentService.startRentOperation(rentOperationModel)).thenReturn(rentOperationModel);
    when(rentOperationMapper.mapToStartResponse(rentOperationModel))
        .thenReturn(RentOperationUtils.getStartRentOperationResponse(ID));

    var content = jsonMapper.getJsonRequest(startRequest);
    var result = mockMvc.perform(post(URL_TEMPLATE_START)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer =
        (StartRentOperationResponse) jsonMapper.getResponse(result, StartRentOperationResponse.class);

    verify(rentService).startRentOperation(rentOperationModel);
    assertEquals(expectedStartResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperFinishResponse() throws Exception {
    rentOperationModel.setToBeRefundAmount(TO_BE_REFUND_AMOUNT);
    rentOperationModel.setToBePaidAmount(TO_BE_PAID_AMOUNT);
    when(rentOperationMapper.mapToModel(finishRequest)).thenReturn(rentOperationModel);
    when(rentService.finishRentOperation(rentOperationModel)).thenReturn(rentOperationModel);
    when(rentOperationMapper.mapToFinishResponse(rentOperationModel))
        .thenReturn(RentOperationUtils.getFinishRentOperationResponse(ID));

    var content = jsonMapper.getJsonRequest(finishRequest);
    var result = mockMvc.perform(post(URL_TEMPLATE_FINISH)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer =
        (FinishRentOperationResponse) jsonMapper.getResponse(result, FinishRentOperationResponse.class);

    verify(rentService).startRentOperation(rentOperationModel);
    assertEquals(expectedFinishResponse, actualResponseFromServer);
  }

}
