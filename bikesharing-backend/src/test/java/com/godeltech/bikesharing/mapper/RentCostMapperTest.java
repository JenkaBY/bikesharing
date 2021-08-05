package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapperImpl;
import com.godeltech.bikesharing.utils.RentCostUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
    RentCostMapperImpl.class,
    EquipmentGroupMapperImpl.class
})
class RentCostMapperTest {
  private static final Long ID = 1L;
  @Autowired
  private RentCostMapper rentCostMapper;

  @Test
  void shouldMapEntityToModel() {
    var rentCost = RentCostUtils.getRentCost();
    rentCost.setId(ID);
    var expected = RentCostUtils.getRentCostModel(ID);

    var actual = rentCostMapper.mapToModel(rentCost);

    assertEquals(expected, actual);
  }
}