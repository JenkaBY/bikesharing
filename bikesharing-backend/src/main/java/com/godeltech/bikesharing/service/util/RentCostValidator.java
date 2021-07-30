package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.exception.ResourceExistingPersistenceException;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.persistence.repository.RentCostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RentCostValidator {
  private final RentCostRepository repository;

  public void checkRentCostExists(RentCostModel rentCostModel) {
    log.info("checkRentCostExists for model: {}", rentCostModel);
    var rentCost = repository.findByEquipmentGroupCode(rentCostModel.getEquipmentGroup().getCode());
    if (rentCost.isPresent()) {
      throw new ResourceExistingPersistenceException(
          String.format("RentCost for equipmentGroupCode: %s already EXISTS - try Update with id: %s",
              rentCostModel.getEquipmentGroup().getCode(), rentCost.get().getId()));
    }
  }
}
