package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import org.mapstruct.Mapper;

@Mapper
public interface EquipmentItemMapper {

  EquipmentItemModel mapToModel(EquipmentItem equipment);

}
