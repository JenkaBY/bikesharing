package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.utils.EquipmentItemUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EquipmentItemMapperImpl.class)
class EquipmentItemMapperTest {
  private static final Long ID = 1L;
  @Autowired
  private EquipmentItemMapper equipmentItemMapper;

  @Test
  void shouldMapEntityToModel() {
    var equipmentItem = EquipmentItemUtils.getEquipmentItem();
    equipmentItem.setId(ID);
    var equipmentItemModel = EquipmentItemUtils.getEquipmentItemModel(ID);

    assertEquals(equipmentItemMapper.mapToModel(equipmentItem), equipmentItemModel);
  }
}