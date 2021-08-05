package com.godeltech.bikesharing.controller.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.godeltech.bikesharing.mapper.GeneralErrorMapper;
import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.request.EquipmentGroupRequest;
import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import com.godeltech.bikesharing.service.admin.EquipmentGroupManagementService;
import com.godeltech.bikesharing.service.util.JsonMapper;
import com.godeltech.bikesharing.utils.EquipmentGroupUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({EquipmentGroupManagementController.class, GeneralErrorMapper.class, JsonMapper.class})
class EquipmentGroupManagementControllerTest {

  private static final String URL_TEMPLATE = "/v1/bikesharing/admin/equipment_group";
  private static final String UPDATED_NAME = "SuperGroupName";
  private static final Long ID = 1L;
  private static EquipmentGroupModel equipmentGroup;
  private static EquipmentGroupResponse expectedResponse;
  private static EquipmentGroupRequest request;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JsonMapper jsonMapper;

  @MockBean
  private EquipmentGroupManagementService managementService;
  @MockBean
  private EquipmentGroupMapper mapper;

  @BeforeEach
  public void setUp() {
    request = EquipmentGroupUtils.getEquipmentGroupRequest();
    equipmentGroup = EquipmentGroupUtils.getEquipmentGroupModel();
    equipmentGroup.setId(ID);
    expectedResponse = EquipmentGroupUtils.getEquipmentGroupResponse(ID);
  }

  @Test
  public void shouldFailWithBabRequestCode() throws Exception {
    request.setName("");
    request.setCode("someCode");

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
    when(mapper.mapToModel(request)).thenReturn(equipmentGroup);
    when(managementService.save(equipmentGroup)).thenReturn(equipmentGroup);
    when(mapper.mapToResponse(equipmentGroup))
        .thenReturn(expectedResponse);

    var content = jsonMapper.getJsonRequest(request);
    var result = mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, EquipmentGroupResponse.class);

    verify(managementService).save(equipmentGroup);
    assertEquals(expectedResponse, actualResponseFromServer);
  }

  @Test
  public void shouldGetProperResponseOnUpdate() throws Exception {
    request.setName(UPDATED_NAME);
    equipmentGroup.setName(UPDATED_NAME);
    expectedResponse.setName(UPDATED_NAME);
    when(mapper.mapToModel(request)).thenReturn(equipmentGroup);
    when(managementService.update(equipmentGroup, ID)).thenReturn(equipmentGroup);
    when(mapper.mapToResponse(equipmentGroup))
        .thenReturn(expectedResponse);

    var content = jsonMapper.getJsonRequest(request);
    var result = mockMvc.perform(put(URL_TEMPLATE + "/" + ID)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    var actualResponseFromServer = jsonMapper.getResponse(result, EquipmentGroupResponse.class);

    verify(managementService).update(equipmentGroup, ID);
    assertEquals(expectedResponse, actualResponseFromServer);
  }
}
