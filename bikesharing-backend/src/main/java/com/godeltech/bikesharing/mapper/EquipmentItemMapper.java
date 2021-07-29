package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.mapper.lookup.EquipmentStatusMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import org.mapstruct.Mapper;

@Mapper(uses = {EquipmentGroupMapper.class, EquipmentStatusMapper.class})
public interface EquipmentItemMapper {

  EquipmentItemModel mapToModel(EquipmentItem equipment);

  EquipmentItem mapToEntity(EquipmentItemModel model);
}
