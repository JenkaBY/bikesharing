package com.godeltech.bikesharing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.bikesharing.models.request.RentOperationRequest;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.RentOperationUtils;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

class RentOperationControllerTest extends AbstractIntegrationTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/rentoperation";
//TODO Use mockito
  @Test
  public void ShouldFailWithNotFoundCode() throws Exception {
    final String content = getJsonRequest(RentOperationUtils.getRentOperationRequest());
    mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  public void ShouldGetProperResponse() throws Exception {
    var request = RentOperationUtils.getRentOperationRequest();
    request.setEquipmentRegistrationNumber("equipment1");
    var content = getJsonRequest(request);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var response = getResponse(result);

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
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
    return LocalDateTime.parse(str, formatter);
  }
}