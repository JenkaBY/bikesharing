package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentCostModel;
import java.util.List;
import java.util.Optional;

public interface RentCostService {
  RentCostModel getByEquipmentGroupCode(String equipmentGroupCode);

  RentCostModel save(RentCostModel rentCostModel);

  RentCostModel getById(Long id);
}
