package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.ClientAccountRequest;
import com.godeltech.bikesharing.models.response.ClientAccountResponse;
import com.godeltech.bikesharing.service.ClientService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ClientAccountManagementController.class, GeneralErrorMapper.class, JsonMapper.class})
class ClientAccountManagementControllerTest {
  private static final String URL_TEMPLATE = "/v1/bikesharing/admin/client";
  private static final String UPDATED_NAME = "SuperMario";
  private static final Long ID = 1L;

  private static ClientAccountModel clientAccount;
  private static ClientAccountResponse expectedResponse;
  private static ClientAccountRequest request;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private ClientService service;
  @MockBean
  private ClientAccountMapper mapper;

  @BeforeEach
  public void setUp() {
    request = ClientAccountUtils.getClientAccountRequest();
    clientAccount = ClientAccountUtils.getClientAccountModel(ID);
    expectedResponse = ClientAccountUtils.getClientAccountResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    request.setName("");
    request.setPhoneNumber("-123456");

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
    when(mapper.mapToModel(request)).thenReturn(clientAccount);
    when(service.save(clientAccount)).thenReturn(clientAccount);
    when(mapper.mapToResponse(clientAccount))
        .thenReturn(expectedResponse);

    var content = jsonMapper.getJsonRequest(request);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, ClientAccountResponse.class);

    verify(service).save(clientAccount);
    assertEquals(expectedResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperResponseOnUpdate() throws Exception {
    request.setName(UPDATED_NAME);
    clientAccount.setName(UPDATED_NAME);
    expectedResponse.setName(UPDATED_NAME);
    when(mapper.mapToModel(request)).thenReturn(clientAccount);
    when(service.update(clientAccount, ID)).thenReturn(clientAccount);
    when(mapper.mapToResponse(clientAccount))
        .thenReturn(expectedResponse);

    var content = jsonMapper.getJsonRequest(request);
    var result = mockMvc.perform(put(URL_TEMPLATE + "/" + ID)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, ClientAccountResponse.class);

    verify(service).update(clientAccount, ID);
    assertEquals(expectedResponse, actualResponseFromServer);
  }

  @Nested
  class GetAllShouldReturnByLookup {

    @Test
    @SneakyThrows
    void shouldReturnListOfClients() {
      MultiValueMap<String, String> queryParams = new LinkedMultiValueMap();
      queryParams.put("lookup", List.of("lookupValue"));
      queryParams.put("page", List.of("1"));
      queryParams.put("size", List.of("10"));
      queryParams.put("sort", List.of("name"));

      Pageable pageable = PageRequest.of(1, 10, Sort.by("name"));
      when(service.findBySearchCriteria("lookupValue", pageable))
              .thenReturn(new PageImpl<ClientAccountModel>(List.of(mock(ClientAccountModel.class))));
       performRequest(URL_TEMPLATE, queryParams)
              .andExpect(status().isOk())
              .andExpectAll(
                      jsonPath("$.size", Matchers.is(1)),
                      jsonPath("$.totalElements", Matchers.is(1)),
                      jsonPath("$.number", Matchers.is(0)),
                      jsonPath("$.content", Matchers.hasSize(1))
              );
    }

    @SneakyThrows
    private ResultActions performRequest(String url, MultiValueMap<String, String> params) {
      return mockMvc.perform(MockMvcRequestBuilders.get(url)
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON)
                      .queryParams(params))
              .andDo(print());
    }

  }
}