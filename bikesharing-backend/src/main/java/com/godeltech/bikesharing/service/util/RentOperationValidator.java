package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.exception.ResourceNotFreeException;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RentOperationValidator {

  public void checkEquipmentItemIsFree(EquipmentItemModel equipmentItemModel) {
    log.info("checkEquipmentItemIsFree for model: {}", equipmentItemModel);
    if (!equipmentItemModel.getEquipmentStatus().getCode().equals(EquipmentItemModel.EQUIPMENT_ITEM_STATUS_FREE)) {
      throw new ResourceNotFreeException(
          String.format("The status is not FREE for equipmentItem: %s",
              equipmentItemModel));
    }
  }
}
