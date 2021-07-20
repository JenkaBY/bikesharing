package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.RentCostModel;
import java.util.List;

public interface RentCostService {
  List<RentCostModel> getByEquipmentGroupCode(String equipmentGroupCode);

  RentCostModel save(RentCostModel rentCostModel);
}
