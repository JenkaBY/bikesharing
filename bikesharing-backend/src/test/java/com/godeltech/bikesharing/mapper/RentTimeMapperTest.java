package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.models.enums.RentTimeUnit;
import com.godeltech.bikesharing.utils.RentTimeModelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RentTimeMapperImpl.class)
class RentTimeMapperTest {
  private static final RentTimeUnit TIME_UNIT_HOUR = RentTimeUnit.HOUR;
  private static final Long TIME_UNIT_AMOUNT = 1L;

  @Autowired
  private RentTimeMapper rentTimeMapper;

  @Test
  void shouldMapRequestToModel() {
    var rentTimeRequest = RentTimeModelUtils.getRentTimeRequest(TIME_UNIT_HOUR, TIME_UNIT_AMOUNT);
    var rentTimeModel = RentTimeModelUtils.getRentTimeModel(TIME_UNIT_HOUR, TIME_UNIT_AMOUNT);

    assertEquals(rentTimeMapper.mapToModel(rentTimeRequest), rentTimeModel);
  }
}