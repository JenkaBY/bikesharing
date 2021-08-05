package com.godeltech.bikesharing.mapper.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.utils.EquipmentStatusUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EquipmentStatusMapperImpl.class)
class EquipmentStatusMapperTest {

  @Autowired
  private EquipmentStatusMapper equipmentStatusMapper;

  @Test
  void shouldMapEntityToModel() {
    var equipmentStatus = EquipmentStatusUtils.getEquipmentStatusFree();
    var expected = EquipmentStatusUtils.getEquipmentStatusFreeModel();

    var actual = equipmentStatusMapper.mapToModel(equipmentStatus);

    assertEquals(expected, actual);
  }
}
