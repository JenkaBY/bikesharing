package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.utils.RentOperationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RentOperationMapperImpl.class)
class RentOperationMapperTest {
  private static final Long ID = 1L;
  @Autowired
  private RentOperationMapper rentOperationMapper;

  @Test
  void shouldMapEntityToModel() {
    var rentOperation = RentOperationUtils.getRentOperation();
    rentOperation.setId(ID);
    rentOperation.getEquipmentItem().setId(ID);
    rentOperation.getClientAccount().setId(ID);
    var expected = RentOperationUtils.getRentOperationModel(ID);

    var actual = rentOperationMapper.mapToModel(rentOperation);

    assertEquals(expected, actual);
  }

  @Test
  void shouldMapModelToResponse() {
    var rentOperation = RentOperationUtils.getRentOperationModel(ID);
    rentOperation.getEquipmentItem().setId(ID);
    rentOperation.getClientAccount().setId(ID);
    var expected = RentOperationUtils.getStartRentOperationResponse(ID);

    var actual = rentOperationMapper.mapToStartResponse(rentOperation);

    assertEquals(expected, actual);
  }

  @Test
  void shouldMapStartRequestToModel() {
    var request = RentOperationUtils.getStartRentOperationRequest();
    var expected = RentOperationUtils.getRentOperationModel(null);

    var actual = rentOperationMapper.mapToModel(request);

    assertNotNull(actual.getClientAccount());
    assertEquals(expected.getClientAccount().getPhoneNumber(), actual.getClientAccount().getPhoneNumber());
    assertNotNull(actual.getEquipmentItem());
    assertEquals(expected.getEquipmentItem().getRegistrationNumber(),
        actual.getEquipmentItem().getRegistrationNumber());
    assertNotNull(actual.getStartTime());
  }

  @Test
  void shouldMapFinishRequestToModel() {
    var request = RentOperationUtils.getFinishRentOperationRequest();
    var expected = RentOperationUtils.getRentOperationModel(null);

    var actual = rentOperationMapper.mapToModel(request);

    assertNotNull(actual.getEquipmentItem());
    assertEquals(expected.getEquipmentItem().getRegistrationNumber(),
        actual.getEquipmentItem().getRegistrationNumber());
    assertNotNull(actual.getFinishedAtTime());
  }
}