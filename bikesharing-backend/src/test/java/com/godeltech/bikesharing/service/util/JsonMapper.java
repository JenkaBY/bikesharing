package com.godeltech.bikesharing.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
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
    return objectMapper.readValue(jsonString, clazz);
  }

  public <T> List<T> getResponseToList(MvcResult result, TypeReference<List<T>> type)
      throws UnsupportedEncodingException, JsonProcessingException {
    var jsonString = result.getResponse().getContentAsString();

    return objectMapper.readValue(jsonString, type);
  }

  public <T> List<T> getResponseFromPage(MvcResult result, TypeReference<List<T>> type)
      throws UnsupportedEncodingException, JsonProcessingException, JSONException {
    var jsonString = result.getResponse().getContentAsString();
    var content = new JSONObject(jsonString).getString("content");
    return objectMapper.readValue(content, type);
  }
}
