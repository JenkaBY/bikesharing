package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EquipmentItemMapper {

  EquipmentItemModel mapToModel(EquipmentItem equipment);

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  EquipmentItem mapToEntity(EquipmentItemModel model);
}
