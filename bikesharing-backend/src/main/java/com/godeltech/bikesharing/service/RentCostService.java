package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentCostModel;

public interface RentCostService {

  RentCostModel getByEquipmentGroupCode(String equipmentGroupCode);

  RentCostModel save(RentCostModel rentCostModel);

  RentCostModel getById(Long id);
}
