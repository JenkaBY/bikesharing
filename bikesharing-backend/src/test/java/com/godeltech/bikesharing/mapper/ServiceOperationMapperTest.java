package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapperImpl;
import com.godeltech.bikesharing.mapper.lookup.EquipmentStatusMapperImpl;
import com.godeltech.bikesharing.mapper.lookup.ServiceTypeMapperImpl;
import com.godeltech.bikesharing.utils.ServiceOperationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
    ServiceOperationMapperImpl.class,
    ServiceTypeMapperImpl.class,
    EquipmentItemMapperImpl.class,
    EquipmentGroupMapperImpl.class,
    EquipmentStatusMapperImpl.class
})
class ServiceOperationMapperTest {

  private static final Long ID = 1L;
  @Autowired
  private ServiceOperationMapper serviceOperationMapper;

  @Test
  void shouldMapEntityToModel() {
    var serviceOperation = ServiceOperationUtils.getServiceOperation(ID);
    serviceOperation.getEquipmentItem().setId(ID);
    serviceOperation.setEndDate(ServiceOperationUtils.END_DATE);
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
  void shouldMapModelToResponse() {
    var serviceOperation = ServiceOperationUtils.getServiceOperationModel(ID);
    serviceOperation.getEquipmentItemModel().setId(ID);
    var expected = ServiceOperationUtils.getStartEquipmentMaintenanceResponse(ID);

    var actual = serviceOperationMapper.mapToStartResponse(serviceOperation);

    assertEquals(expected, actual);
  }

  @Test
  void shouldMapModelToFinishResponse() {
    var serviceOperation = ServiceOperationUtils.getServiceOperationModel(ID);
    serviceOperation.getEquipmentItemModel().setId(ID);
    var expected = ServiceOperationUtils.getFinishEquipmentMaintenanceResponse(ID);

    var actual = serviceOperationMapper.mapToFinishResponse(serviceOperation);

    assertEquals(expected, actual);
  }
}
