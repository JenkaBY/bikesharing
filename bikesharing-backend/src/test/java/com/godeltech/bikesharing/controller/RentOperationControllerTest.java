package com.godeltech.bikesharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.models.request.RentTimeRequest;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.service.RentService;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest({RentOperationController.class, GeneralErrorMapper.class})
public class RentOperationControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/rentoperation";
  private static final Long ID = 1L;
  private static final RentTimeRequest WRONG_RENT_TIME_REQUEST =
      RentTimeModelUtils.getRentTimeRequest(RentTimeUnit.DAY, 3L);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private RentService rentService;
  @MockBean
  private RentOperationMapper rentOperationMapper;

  private StartRentOperationRequest request;
  private StartRentOperationResponse response;

  @BeforeEach
  public void setUp() {
    request = RentOperationUtils.getStartRentOperationRequest();
    response = RentOperationUtils.getStartRentOperationResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    request.setEquipmentRegistrationNumber("");
    request.setRentTimeRequest(WRONG_RENT_TIME_REQUEST);
    var content = getJsonRequest(request);
    mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldGetProperResponse() throws Exception {
    var rentOperationModel = RentOperationUtils.getRentOperationModel(ID);
    when(rentOperationMapper.mapToModel(request)).thenReturn(rentOperationModel);
    when(rentService.startRentOperation(rentOperationModel)).thenReturn(rentOperationModel);
    when(rentOperationMapper.mapToResponse(rentOperationModel))
        .thenReturn(RentOperationUtils.getStartRentOperationResponse(ID));

    var content = getJsonRequest(request);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var responseFromServer = getResponse(result);

    verify(rentService).startRentOperation(rentOperationModel);
    assertEquals(responseFromServer, response);
  }

  private String getJsonRequest(StartRentOperationRequest request) throws JsonProcessingException {
    return objectMapper.writeValueAsString(request);
  }

  private StartRentOperationResponse getResponse(MvcResult result)
      throws UnsupportedEncodingException, JsonProcessingException {
    var jsonString = result.getResponse().getContentAsString();
    return objectMapper.readValue(jsonString, StartRentOperationResponse.class);
  }

}
