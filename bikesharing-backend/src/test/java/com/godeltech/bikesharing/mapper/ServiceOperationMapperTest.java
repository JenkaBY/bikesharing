package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ServiceOperationMapperImpl.class)
class ServiceOperationMapperTest {
  private static final Long ID = 1L;
  @Autowired
  private ServiceOperationMapper serviceOperationMapper;

  @Test
  void shouldMapEntityToModel() {
    var serviceOperation = ServiceOperationUtils.getServiceOperation(ID);
    serviceOperation.getEquipmentItem().setId(ID);
    var expected = ServiceOperationUtils.getServiceOperationModel(ID);

    var actual = serviceOperationMapper.mapToModel(serviceOperation);

    assertEquals(expected, actual);
  }

  @Test
  void shouldMapRequestToModel() {
    var serviceOperation = ServiceOperationUtils.getStartEquipmentMaintenanceRequest();
    var expected = ServiceOperationUtils.getServiceOperationModel(null);

    var actual = serviceOperationMapper.mapToModel(serviceOperation);

    assertEquals(expected.getServiceTypeModel().getCode(), actual.getServiceTypeModel().getCode());
    assertEquals(expected.getEquipmentItemModel().getRegistrationNumber(),
        actual.getEquipmentItemModel().getRegistrationNumber());
    assertEquals(expected.getIssueDescription(), actual.getIssueDescription());
  }

  @Test
  void shouldMapEntityToResponse() {
    var serviceOperation = ServiceOperationUtils.getServiceOperation(ID);
    serviceOperation.getEquipmentItem().setId(ID);
    var expected = ServiceOperationUtils.getEquipmentHandlingResponse(ID);

    var actual = serviceOperationMapper.mapToResponse(serviceOperation);

    assertEquals(expected, actual);
  }
}