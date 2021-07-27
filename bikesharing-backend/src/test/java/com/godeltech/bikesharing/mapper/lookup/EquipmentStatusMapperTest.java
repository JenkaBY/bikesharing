package com.godeltech.bikesharing.mapper.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.utils.EquipmentStatusUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EquipmentStatusMapperImpl.class)
class EquipmentStatusMapperTest {
  private static final Long ID = 1L;
  @Autowired
  private EquipmentStatusMapper equipmentStatusMapper;

  @Test
  void shouldMapEntityToModel() {
    var equipmentStatus = EquipmentStatusUtils.getEquipmentStatus();
    var expected = EquipmentStatusUtils.getEquipmentStatusModel();

    var actual = equipmentStatusMapper.mapToModel(equipmentStatus);
//    TODO please pay attention to assertEquals() method signature. It has "expected" object as the first parameter and
//    "actual" as the second one. You mixed them up
    assertEquals(expected, actual);
  }
}
