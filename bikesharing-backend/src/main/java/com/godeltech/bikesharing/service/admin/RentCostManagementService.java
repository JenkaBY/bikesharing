package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.RentCostModel;

public interface RentCostManagementService {

  RentCostModel saveWithGroupCode(RentCostModel rentCostModel, String equipmentCode);

  RentCostModel update(RentCostModel model, Long id);
}
