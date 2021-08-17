package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.EquipmentTimeInUseModel;
import com.godeltech.bikesharing.models.TimeInUseModel;
import com.godeltech.bikesharing.persistence.entity.TimeInUse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {
    EquipmentItemMapper.class
})
public interface TimeInUseMapper {

  TimeInUseModel mapToModel(TimeInUse entity);

  @Mapping(target = "equipmentItem.id", source = "model.equipmentItemId")
  TimeInUseModel mapToModel(EquipmentTimeInUseModel model);

  @Mapping(target = "minutesInUse", source = "minutesInuse")
  @Mapping(target = "equipmentItem", source = "equipmentItemModel")
  TimeInUse mapToEntity(EquipmentItemModel equipmentItemModel, Long minutesInuse);

  TimeInUse mapToEntity(TimeInUseModel model);
}
