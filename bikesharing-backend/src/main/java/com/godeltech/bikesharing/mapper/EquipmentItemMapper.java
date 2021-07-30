package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.mapper.lookup.EquipmentStatusMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EquipmentGroupMapper.class, EquipmentStatusMapper.class})
public interface EquipmentItemMapper {

  EquipmentItemModel mapToModel(EquipmentItem equipment);

  EquipmentItem mapToEntity(EquipmentItemModel model);

  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "name", source = "model.name")
  @Mapping(target = "equipmentGroup", source = "equipmentGroupModel")
  @Mapping(target = "equipmentStatus", source = "equipmentStatusModel")
  EquipmentItem mapToEntity(EquipmentItemModel model,
                            EquipmentGroupModel equipmentGroupModel,
                            EquipmentStatusModel equipmentStatusModel);
}
