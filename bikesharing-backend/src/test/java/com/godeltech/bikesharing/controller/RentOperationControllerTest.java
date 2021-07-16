package com.godeltech.bikesharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.bikesharing.models.request.RentOperationRequest;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
import com.godeltech.bikesharing.service.RentService;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(RentOperationController.class)
public class RentOperationControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/rentoperation";
  private static final Long ID = 1L;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RentService rentService;

  private RentOperationRequest request;
  private RentOperationResponse response;

  @BeforeEach
  public void setUp() {
    request = RentOperationUtils.getRentOperationRequest();
    response = RentOperationUtils.getRentOperationResponse(ID);
  }

  @Test
  public void ShouldFailWithBabRequestCode() throws Exception {
    request.setEquipmentRegistrationNumber("");
    var content = getJsonRequest(request);
    mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void ShouldGetProperResponse() throws Exception {
    when(rentService.startRentOperation(request)).thenReturn(response);

    var content = getJsonRequest(request);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var responseFromServer = getResponse(result);
    verify(rentService).startRentOperation(request);
    assertEquals(responseFromServer, response);
  }

  private String getJsonRequest(RentOperationRequest request) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(request);
  }

  private RentOperationResponse getResponse(MvcResult result) throws UnsupportedEncodingException, JSONException {
    var response = new RentOperationResponse();
    var jsonString = result.getResponse().getContentAsString();
    var jsonObject = new JSONObject(jsonString);
    response.setId(jsonObject.getLong("id"));
    response.setClientPhoneNumber(jsonObject.getString("clientPhoneNumber"));
    response.setDeposit(jsonObject.getLong("deposit"));
    response.setEquipmentRegistrationNumber(jsonObject.getString("equipmentRegistrationNumber"));
    response.setStartTime(parseFromString(jsonObject.getString("startTime")));
    response.setEndTime(parseFromString(jsonObject.getString("endTime")));
    return response;
  }

  private LocalDateTime parseFromString(String str) {
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    return LocalDateTime.parse(str, formatter);
  }
}