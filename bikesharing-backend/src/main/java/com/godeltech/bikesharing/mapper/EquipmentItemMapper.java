package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.mapper.lookup.EquipmentStatusMapper;
import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.request.EquipmentItemRequest;
import com.godeltech.bikesharing.models.response.EquipmentItemResponse;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EquipmentGroupMapper.class, EquipmentStatusMapper.class})
public interface EquipmentItemMapper {

  EquipmentItemModel mapToModel(EquipmentItem equipment);

  @Mapping(target = "comments", source = "request.comment")
  @Mapping(target = "equipmentGroup", source = "request.equipmentGroupCode")
  @Mapping(target = "equipmentStatus", source = "request.equipmentStatusCode")
  EquipmentItemModel mapToModel(EquipmentItemRequest request);

  EquipmentItem mapToEntity(EquipmentItemModel model);

  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "name", source = "model.name")
  @Mapping(target = "equipmentGroup", source = "equipmentGroup")
  @Mapping(target = "equipmentStatus", source = "equipmentStatus")
  EquipmentItem mapToEntity(EquipmentItemModel model, EquipmentGroupModel equipmentGroup,
      EquipmentStatusModel equipmentStatus);

  EquipmentItemResponse mapToResponse(EquipmentItemModel model);
}
