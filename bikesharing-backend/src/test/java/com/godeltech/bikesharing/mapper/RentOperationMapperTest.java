package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    var rentOperationModel = RentOperationUtils.getRentOperationModel(ID);

    assertEquals(rentOperationMapper.mapToModel(rentOperation), rentOperationModel);
  }

  @Test
  void shouldMapModelToEntity() {
    var rentOperation = RentOperationUtils.getRentOperation();
    rentOperation.setId(ID);
    rentOperation.getEquipmentItem().setId(ID);
    rentOperation.getClientAccount().setId(ID);
    var rentOperationModel = RentOperationUtils.getRentOperationModel(ID);

    assertEquals(rentOperationMapper.mapToEntity(rentOperationModel), rentOperation);
  }
}