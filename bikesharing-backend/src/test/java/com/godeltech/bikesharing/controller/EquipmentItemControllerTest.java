package com.godeltech.bikesharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.response.EquipmentItemResponse;
import com.godeltech.bikesharing.service.EquipmentItemService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({EquipmentItemController.class, GeneralErrorMapper.class, JsonMapper.class})
class EquipmentItemControllerTest {

  private static final String URL_TEMPLATE = "/v1/bikesharing/equipment_item/page/1";
  private static final Long ID = 1L;
  private static final int PAGE_NUM = 1;
  private static final String CODE_FREE = EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE;
  private static final EquipmentItemModel model = EquipmentItemUtils.getEquipmentItemModel(ID);
  private static final EquipmentItemResponse response = EquipmentItemUtils.getEquipmentItemResponse(ID);

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private EquipmentItemService service;
  @MockBean
  private EquipmentItemMapper mapper;

  @Test
  public void shouldGetProperResponse() throws Exception {
    when(mapper.mapToResponse(model)).thenReturn(response);
    when(service.getAllByStatusCode(CODE_FREE, PAGE_NUM)).thenReturn(new PageImpl<>(List.of(model)));

    var result = mockMvc.perform(get(URL_TEMPLATE + "?statusCode=" + CODE_FREE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var type = new TypeReference<List<EquipmentItemResponse>>() {};
    var actualResponseFromServer = jsonMapper.getResponseToList(result, type);

    verify(service).getAllByStatusCode(CODE_FREE,PAGE_NUM);
    assertEquals(List.of(response), actualResponseFromServer);
  }

}