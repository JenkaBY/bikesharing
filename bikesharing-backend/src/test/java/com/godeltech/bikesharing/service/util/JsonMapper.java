package com.godeltech.bikesharing.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@Component
public class JsonMapper {

  @Autowired
  private ObjectMapper objectMapper;

  public String getJsonRequest(Object request) throws JsonProcessingException {
    return objectMapper.writeValueAsString(request);
  }

  public <T> T getResponse(MvcResult result, Class<T> clazz)
      throws UnsupportedEncodingException, JsonProcessingException {
    var jsonString = result.getResponse().getContentAsString();
    return (T) objectMapper.readValue(jsonString, clazz);
  }
}
