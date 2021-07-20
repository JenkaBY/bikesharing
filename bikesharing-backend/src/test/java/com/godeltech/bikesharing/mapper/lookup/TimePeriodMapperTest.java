package com.godeltech.bikesharing.mapper.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.utils.EquipmentStatusUtils;
import com.godeltech.bikesharing.utils.TimePeriodUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TimePeriodMapperImpl.class)
class TimePeriodMapperTest {
  private static final Long ID = 1L;
  @Autowired
  private TimePeriodMapper equipmentStatusMapper;

  @Test
  void shouldMapEntityToModel() {
    var timePeriod = TimePeriodUtils.getTimePeriod();
    var timePeriodModel = TimePeriodUtils.getTimePeriodModel();

    assertEquals(equipmentStatusMapper.mapToModel(timePeriod), timePeriodModel);
  }
}